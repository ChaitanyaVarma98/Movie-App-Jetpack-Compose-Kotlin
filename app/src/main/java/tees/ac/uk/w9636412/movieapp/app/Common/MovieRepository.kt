package tees.ac.uk.w9636412.movieapp.app.Common

import tees.ac.uk.w9636412.movieapp.data.Movies

interface MovieRepository {
    suspend fun getMovies(): ResultStatus<Movies>

}