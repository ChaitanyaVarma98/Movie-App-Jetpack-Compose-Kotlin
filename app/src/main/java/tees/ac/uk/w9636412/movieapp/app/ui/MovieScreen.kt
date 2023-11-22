package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import tees.ac.uk.w9636412.movieapp.model.MovieScreenViewModel

@Composable
fun MovieScreen(movieScreenViewModel: MovieScreenViewModel = viewModel()) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MovieAppToolBar(toolbarTitle = "Movie App", clickedLogout = { movieScreenViewModel.LogoutUser()} )}
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


@Composable
fun MovieAppToolBar(
    toolbarTitle: String, clickedLogout: () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.LightGray,
        title = {
            Text(
                text = toolbarTitle, color = Color.Black
            )
        },
        actions = {
            IconButton(onClick = {
                clickedLogout.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout Button",
                )
            }
        }
    )
}
