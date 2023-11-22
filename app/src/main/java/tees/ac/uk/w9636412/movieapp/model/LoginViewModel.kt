package tees.ac.uk.w9636412.movieapp.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen

class LoginViewModel : ViewModel()
{
    private val TAG = LoginViewModel::class.simpleName
    var loginUIState = mutableStateOf(LoginStateModel())
    var emailPasswordChecked = mutableStateOf(false)
    var loginStatus = mutableStateOf(false)

    fun onEvent(state: LoginEventTrigger) {
        when (state) {
            is LoginEventTrigger.EmailTrigger -> {
                loginUIState.value = loginUIState.value.copy(
                    email = state.email
                )
            }

            is LoginEventTrigger.PasswordTrigger -> {
                loginUIState.value = loginUIState.value.copy(
                    password = state.password
                )
            }

            is LoginEventTrigger.LoginButtonClicked -> {
                Log.d(TAG,"Clicked login button")
                FirebaseLoginSetup()
            }
        }
    }

    private fun FirebaseLoginSetup() {
        loginStatus.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        Log.d(TAG,email +" "+password)
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Log.d(TAG,"success")
                    loginStatus.value = false
                    MovieAppRouter.navigateTo(MovieAppScreen.MovieScreen)
                }
                Log.d(TAG,"fail")
            }
            .addOnFailureListener {
                loginStatus.value = false
            }
    }
}


data class LoginStateModel(
    var email  :String = "",
    var password  :String = ""

)

sealed class LoginEventTrigger{
    data class EmailTrigger(val email:String): LoginEventTrigger()
    data class PasswordTrigger(val password: String) : LoginEventTrigger()
    object LoginButtonClicked : LoginEventTrigger()
}