package tees.ac.uk.w9636412.movieapp.app.Services

import tees.ac.uk.w9636412.movieapp.data.Movies
import tees.ac.uk.w9636412.movieapp.data.ResultStatus

interface MovieRepository {
    suspend fun getMovies(): ResultStatus<Movies>

}