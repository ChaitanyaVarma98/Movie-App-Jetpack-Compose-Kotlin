package tees.ac.uk.w9636412.movieapp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import tees.ac.uk.w9636412.movieapp.app.Common.MovieRepositoryImp
import tees.ac.uk.w9636412.movieapp.app.Common.NetworkService
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen
import tees.ac.uk.w9636412.movieapp.data.Results

class MovieScreenViewModel : ViewModel(){

    var newsList = mutableStateOf<List<Results>>(listOf())
    val newsRepository = MovieRepositoryImp(NetworkService.movieService)

    private val _movieDetails: MutableState<Results> = mutableStateOf(Results())
    val movieDetails:MutableState<Results> = _movieDetails

    fun setMovie(data:Results){
        _movieDetails.value = data
        MovieAppRouter.navigateTo(MovieAppScreen.MovieDetailScreen)
    }



    fun LogoutUser() {
        val LoginStatus = FirebaseAuth.getInstance()
        LoginStatus.signOut()
        val loginListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                MovieAppRouter.navigateTo(MovieAppScreen.LoginScreen)
            }
        }
        LoginStatus.addAuthStateListener(loginListener)
    }

    fun getMovies(){
        viewModelScope.launch {
            try
            {
                val response = newsRepository.getMovies()
                if (!response.data?.results.isNullOrEmpty())
                {
                    newsList.value = response.data!!.results
                }
                else {
                }
            } catch (e: Exception) {

            }
        }
    }
}