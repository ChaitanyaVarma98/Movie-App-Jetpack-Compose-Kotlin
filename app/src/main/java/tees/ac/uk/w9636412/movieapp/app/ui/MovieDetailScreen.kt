package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import tees.ac.uk.w9636412.movieapp.R
import tees.ac.uk.w9636412.movieapp.app.MovieAppRouter
import tees.ac.uk.w9636412.movieapp.app.MovieAppScreen
import tees.ac.uk.w9636412.movieapp.app.Services.ApiService
import tees.ac.uk.w9636412.movieapp.data.Results
import tees.ac.uk.w9636412.movieapp.model.MovieScreenViewModel

@Composable
fun MovieDetailScreen(
    movieModel: MovieScreenViewModel = viewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val response = movieModel.movieDetails.value
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MovieAppToolBar(
                toolbarTitle = "Movie App",
                clickedLogout = { movieModel.SignOutUser() })
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
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(300.dp)
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

                // New row with two images
                Row(
                    modifier = Modifier.padding(5.dp, 16.dp, 5.dp, 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (movie in movieModel.randomMovieList.value) {
                        MoiveColumn(movie)
                    }
                }
            }
        }
    }
}


@Composable
fun MoiveColumn (movie : Results){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(
                data = "${ApiService.BASE_POSTER_URL}${movie.poster_path ?: ""}",
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(CornerSize(20.dp)))
        )
        Text(
            modifier = Modifier.padding(5.dp),
            text = movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_XL)
@Composable
fun DefaultPreview() {
    val mockViewModel = MovieScreenViewModel()
    MovieDetailScreen(movieModel = mockViewModel)
}