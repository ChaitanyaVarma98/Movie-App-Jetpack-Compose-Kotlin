package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import tees.ac.uk.w9636412.movieapp.app.components.AppToolbar
import tees.ac.uk.w9636412.movieapp.model.LoginViewModel
import tees.ac.uk.w9636412.movieapp.model.MovieScreenViewModel

@Composable
fun MovieScreen(movieScreenViewModel: MovieScreenViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppToolbar(toolbarTitle = "Movie App",
                logoutButtonClicked = {
                    movieScreenViewModel.LogoutUser()
                }
            )
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}

