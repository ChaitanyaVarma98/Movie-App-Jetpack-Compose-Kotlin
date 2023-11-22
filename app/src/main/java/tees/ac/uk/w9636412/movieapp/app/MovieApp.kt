package tees.ac.uk.w9636412.movieapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import tees.ac.uk.w9636412.movieapp.app.ui.LoginScreen
import tees.ac.uk.w9636412.movieapp.app.ui.MovieScreen
import tees.ac.uk.w9636412.movieapp.app.ui.RegisterScreen

@Composable
fun MovieApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

//        if (homeViewModel.isUserLoggedIn.value == true) {
//            NewsAppRouter.navigateTo(NewsAppScreen.HomeScreen)
//        }

        Crossfade(targetState = MovieAppRouter.current) { current ->
            when (current.value) {
                is MovieAppScreen.RegisterScreen -> {
                    RegisterScreen()
                }

                is MovieAppScreen.LoginScreen -> {
                    LoginScreen()
                }

                is MovieAppScreen.MovieScreen -> {
                    MovieScreen()
                }
            }
        }
    }
}