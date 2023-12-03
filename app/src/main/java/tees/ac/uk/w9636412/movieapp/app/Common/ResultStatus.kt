package tees.ac.uk.w9636412.movieapp.app.Common
sealed class ResultStatus<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T?): ResultStatus<T>(data = data)
    class Error<T>(message: String?): ResultStatus<T>(message = message)
}