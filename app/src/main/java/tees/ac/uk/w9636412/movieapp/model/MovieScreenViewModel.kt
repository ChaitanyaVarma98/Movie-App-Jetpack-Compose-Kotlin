package tees.ac.uk.w9636412.movieapp.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import tees.ac.uk.w9636412.movieapp.app.Services.MovieRepositoryImp
import tees.ac.uk.w9636412.movieapp.app.Services.NetworkService
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen
import tees.ac.uk.w9636412.movieapp.data.Results

class MovieScreenViewModel : ViewModel(){

    var newMovieList = mutableStateOf<List<Results>>(listOf())
    var randomMovieList = mutableStateOf<List<Results>>(listOf())
    val newsRepository = MovieRepositoryImp(NetworkService.movieService)

    private val _movieDetails: MutableState<Results> = mutableStateOf(Results())
    val movieDetails:MutableState<Results> = _movieDetails

    fun setMovie(data:Results){
        _movieDetails.value = data
        randomMovieList.value = getRandomMovies()
        MovieAppRouter.navigateTo(MovieAppScreen.MovieDetailScreen)
    }
    fun getMovies(){
        viewModelScope.launch {
            newMovieList.value = newsRepository.getMovies().data!!.results
        }
    }

    fun SignOutUser() {
        val LoginStatus = FirebaseAuth.getInstance()
        LoginStatus.signOut()
        val loginListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                MovieAppRouter.navigateTo(MovieAppScreen.LoginScreen)
            }
        }
        LoginStatus.addAuthStateListener(loginListener)
    }

    fun getRandomMovies(): List<Results> {
        // Check if newMovieList has less than 3 items
        if (newMovieList.value.size <= 3) {
            return newMovieList.value
        } else {
            return newMovieList.value.shuffled().take(3)
        }
    }

}