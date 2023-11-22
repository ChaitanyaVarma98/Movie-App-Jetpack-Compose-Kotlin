package tees.ac.uk.w9636412.movieapp.model

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen

class MovieScreenViewModel : ViewModel(){
    fun LogoutUser() {
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                MovieAppRouter.navigateTo(MovieAppScreen.LoginScreen)
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}