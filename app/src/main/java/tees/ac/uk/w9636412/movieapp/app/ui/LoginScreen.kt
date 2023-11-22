package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tees.ac.uk.w9636412.movieapp.R
import tees.ac.uk.w9636412.movieapp.app.components.ButtonComponent
import tees.ac.uk.w9636412.movieapp.app.components.ClickableLoginTextComponent
import tees.ac.uk.w9636412.movieapp.app.components.DividerTextComponent
import tees.ac.uk.w9636412.movieapp.app.components.HeadingTextComponent
import tees.ac.uk.w9636412.movieapp.app.components.NormalTextComponent
import tees.ac.uk.w9636412.movieapp.app.components.PasswordTextFieldComponent
import tees.ac.uk.w9636412.movieapp.app.components.TextFieldComponent


@Composable
fun LoginScreen()
{
    Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
    ) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            NormalTextComponent(value = "Login")
            HeadingTextComponent(value =  "Welcome")
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldComponent(labelValue = "Email Address",
                painterResource(id = R.drawable.message),
                onTextChanged = {
                    //loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                },
                //errorStatus = loginViewModel.loginUIState.value.emailError
            )
            PasswordTextFieldComponent(
                labelValue = "Password",
                painterResource(id = R.drawable.passwordlock),
                onTextSelected = {
                    //loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                },
                //errorStatus = loginViewModel.loginUIState.value.passwordError
            )
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent(
                value = "Click Login",
                onButtonClicked = {
                    //loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                },
                //isEnabled = loginViewModel.emailPasswordChecked.value
            )
            Spacer(modifier = Modifier.height(20.dp))
            DividerTextComponent()
            ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                //NewsAppRouter.navigateTo(NewsAppScreen.SignUpScreen)
            })
        }
    }
}
}