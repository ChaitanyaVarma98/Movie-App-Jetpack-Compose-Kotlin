package tees.ac.uk.w9636412.movieapp.data

data class Movies(
    val page: Int,
    val results: List<Results>,
    val total_pages: Int,
    val total_results: Int
)