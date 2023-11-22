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
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.w9636412.movieapp.R
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen
import tees.ac.uk.w9636412.movieapp.app.Common.ButtonComponent
import tees.ac.uk.w9636412.movieapp.app.Common.ClickTextComponent
import tees.ac.uk.w9636412.movieapp.app.Common.DividerTextComponent
import tees.ac.uk.w9636412.movieapp.app.Common.HeadingTextComponent
import tees.ac.uk.w9636412.movieapp.app.Common.PasswordTextFieldComponent
import tees.ac.uk.w9636412.movieapp.app.Common.TextFieldComponent
import tees.ac.uk.w9636412.movieapp.model.LoginEventTrigger
import tees.ac.uk.w9636412.movieapp.model.LoginViewModel


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
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

                HeadingTextComponent(value = "Welcome to MovieApp Login")
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = "Email Address",
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.onEvent(LoginEventTrigger.EmailTrigger(it))
                    }
                )
                PasswordTextFieldComponent(
                    labelValue = "Password",
                    painterResource(id = R.drawable.passwordlock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginEventTrigger.PasswordTrigger(it))
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(
                    value = "Click to Login",
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginEventTrigger.LoginButtonClicked)
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                ClickTextComponent(tryingToLogin = false, onTextSelected = {
                    MovieAppRouter.navigateTo(MovieAppScreen.RegisterScreen)
                })
            }
        }
    }
}