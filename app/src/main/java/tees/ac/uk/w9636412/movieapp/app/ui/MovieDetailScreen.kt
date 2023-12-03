package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import tees.ac.uk.w9636412.movieapp.R
import tees.ac.uk.w9636412.movieapp.app.Common.ApiService
import tees.ac.uk.w9636412.movieapp.data.Results
import tees.ac.uk.w9636412.movieapp.model.MovieScreenViewModel

@Composable
fun MovieDetailScreen(
    movieScreenViewModel: MovieScreenViewModel = viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val response = movieScreenViewModel.movieDetails.value
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MovieAppToolBar(
                toolbarTitle = "Movie App",
                clickedLogout = { movieScreenViewModel.LogoutUser() })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = "${ApiService.BASE_POSTER_URL}${response.poster_path ?: ""}",
                        builder = {
                            crossfade(true)
                            placeholder(R.drawable.ic_launcher_foreground)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally).size(300.dp)
                )
                Text(text = response.original_title ?: "-", style = MaterialTheme.typography.h6)
                Text(
                    text = "" + response.vote_average + " rating" ?: "0",
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = response.overview ?: "-",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}