package tees.ac.uk.w9636412.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import tees.ac.uk.w9636412.movieapp.app.MovieApp
import tees.ac.uk.w9636412.movieapp.model.MovieScreenViewModel

class MainActivity : ComponentActivity() {
    val viewModel: MovieScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}
