package tees.ac.uk.w9636412.movieapp.model

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen

class RegisterViewModel : ViewModel()
{
    private val TAG = RegisterViewModel::class.simpleName
    var registerState = mutableStateOf(RegisterStateModel())
    var signUpState = mutableStateOf(false)

    fun onEvent(state: RegisterTrigger) {
        when (state) {
            is RegisterTrigger.FirstNameUpdated -> {
                registerState.value  = registerState.value.copy(
                    firstName = state.firstName
                )
            }

            is RegisterTrigger.LastNameUpdated -> {
                registerState.value = registerState.value.copy(
                    lastName = state.lastName
                )
            }

            is RegisterTrigger.EmailUpdated -> {
                registerState.value = registerState.value.copy(
                    email = state.email
                )
            }


            is RegisterTrigger.PasswordUpdated -> {
                registerState.value = registerState.value.copy(
                    password = state.password
                )
            }

            is RegisterTrigger.RegisterButtonClicked -> {
                Log.d(TAG,"Clicked")
                FirebaseUserSignUp()
            }
        }
    }


    private fun FirebaseUserSignUp() {
        Log.d(TAG,"email : "+ registerState.value.email)
        createUserInFirebase(
            email = registerState.value.email,
            password = registerState.value.password
        )
    }

    private fun createUserInFirebase(email: String, password: String) {
        signUpState.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                signUpState.value = false
                if (it.isSuccessful)
                {
                    Log.d(TAG,"Successfull")
                    MovieAppRouter.navigateTo(MovieAppScreen.MovieScreen)
                }
                Log.d(TAG,"Failed")
            }
            .addOnFailureListener {}
    }
}

data class RegisterStateModel(
    var firstName :String = "",
    var lastName  :String = "",
    var email  :String = "",
    var password  :String = "",
    var privacyPolicyAccepted :Boolean = false
)


sealed class RegisterTrigger {
    data class FirstNameUpdated(val firstName:String) : RegisterTrigger()
    data class LastNameUpdated(val lastName:String) : RegisterTrigger()
    data class EmailUpdated(val email:String): RegisterTrigger()
    data class PasswordUpdated(val password: String) : RegisterTrigger()
    object RegisterButtonClicked : RegisterTrigger()
}