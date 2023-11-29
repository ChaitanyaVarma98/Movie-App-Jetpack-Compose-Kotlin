package tees.ac.uk.w9636412.movieapp.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import tees.ac.uk.w9636412.movieapp.R
import tees.ac.uk.w9636412.movieapp.data.Movies
import tees.ac.uk.w9636412.movieapp.model.MovieScreenViewModel
import tees.ac.uk.w9636412.movieapp.retrofit.ApiService

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
            Column(modifier = Modifier.fillMaxSize())
            {
                MoviesList(movieScreenViewModel.moviesList.value, onClickView ={}
                )
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



@Composable
fun MoviesList(
    movieList: Movies,
    onClickView : () -> Unit
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(movieList){movie ->
            MovieCardList(
                results = movie,
                onClickView = onClickView
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieCardList(results: Movies.Results, onClickView: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = { onClickView() }

    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${ApiService.BASE_POSTER_URL}${results.poster_path}")
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
            }
                Text(text = "results.original_title!!", style = typography.h6, textAlign = TextAlign.Center)
                Text(text = "results.overview!!", style = typography.caption, textAlign = TextAlign.Center)

            }
        }
    }