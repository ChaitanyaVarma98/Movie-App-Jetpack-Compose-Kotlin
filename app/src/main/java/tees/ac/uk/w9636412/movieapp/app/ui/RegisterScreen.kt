package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import tees.ac.uk.w9636412.movieapp.R
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen
import tees.ac.uk.w9636412.movieapp.app.Common.ButtonComponent
import tees.ac.uk.w9636412.movieapp.app.Common.ClickTextComponent
import tees.ac.uk.w9636412.movieapp.app.Common.HeadingTextComponent
import tees.ac.uk.w9636412.movieapp.app.Common.PasswordTextFieldComponent
import tees.ac.uk.w9636412.movieapp.app.Common.TextFieldComponent
import tees.ac.uk.w9636412.movieapp.model.RegisterTrigger
import tees.ac.uk.w9636412.movieapp.model.RegisterViewModel

@Composable
fun RegisterScreen(registrationViewModel: RegisterViewModel = viewModel()) {
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
            Column(modifier = Modifier.fillMaxSize()) {

                HeadingTextComponent("Create New Account MovieApp")
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = "Enter the First Name",
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegisterTrigger.FirstNameUpdated(it))
                    }
                )
                TextFieldComponent(
                    labelValue = "Enter the Last Name",
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegisterTrigger.LastNameUpdated(it))
                    }
                )

                TextFieldComponent(
                    labelValue = "Enter the Email",
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        registrationViewModel.onEvent(RegisterTrigger.EmailUpdated(it))
                    }
                )

                PasswordTextFieldComponent(
                    labelValue = "Enter the Password",
                    painterResource = painterResource(id = R.drawable.passwordlock),
                    onTextSelected = {
                        registrationViewModel.onEvent(RegisterTrigger.PasswordUpdated(it))
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = "Register",
                    onButtonClicked = {
                        registrationViewModel.onEvent(RegisterTrigger.RegisterButtonClicked)
                    },
                )
                Spacer(modifier = Modifier.height(20.dp))
                ClickTextComponent(tryingToLogin = true, onTextSelected = {
                    MovieAppRouter.navigateTo(MovieAppScreen.LoginScreen)
                })
            }
        }

        if(registrationViewModel.signUpState.value) {
            CircularProgressIndicator()
        }
    }
}

