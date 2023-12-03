package tees.ac.uk.w9636412.movieapp.app.Common

import android.util.Log
import tees.ac.uk.w9636412.movieapp.data.Movies

class MovieRepositoryImp(
    private val service: ApiService
): MovieRepository {

    private val TAG = MovieRepositoryImp::class.simpleName
    override suspend fun getMovies(): ResultStatus<Movies> {
        val response = service.getMoviesList()
        Log.d(TAG, "Repo Implementation - " + response.total_results)
        if (response.results.isNotEmpty()) {
            Log.d(TAG, "Repo Implementation - pass " + response.total_results)
            return ResultStatus.Response(response)
        } else {
            Log.d(TAG, "Repo Implementation - failed " + response.total_results)
            return ResultStatus.Message("Failed")
        }
    }
}