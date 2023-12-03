package tees.ac.uk.w9636412.movieapp.app.Common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tees.ac.uk.w9636412.movieapp.app.Common.ApiService.Companion.BASE_URL

object NetworkService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val movieService: ApiService = retrofit.create(ApiService::class.java)
}