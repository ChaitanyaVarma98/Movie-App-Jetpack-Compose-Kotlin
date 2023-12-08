package tees.ac.uk.w9636412.movieapp.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed  class MovieAppScreen {
    object RegisterScreen : MovieAppScreen()
    object LoginScreen : MovieAppScreen()
    object MovieScreen : MovieAppScreen()
    object MovieDetailScreen : MovieAppScreen()
}

object MovieAppRouter {

    var current: MutableState<MovieAppScreen> = mutableStateOf(MovieAppScreen.MovieScreen)

    fun navigateTo(destination : MovieAppScreen){
        current.value = destination
    }
}