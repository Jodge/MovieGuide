package com.jodge.movies.api

import com.jodge.movies.data.models.Movie
import com.jodge.movies.data.models.Review
import com.jodge.movies.data.models.Video
import com.jodge.movies.data.models.response.ApiResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface TheMovieDbApi {

    @GET("/3/discover/movie")
    fun popularMovies(@QueryMap map: Map<String, String>): Observable<ApiResponse<Movie>>


    @GET("3/discover/movie")
    fun highestRatedMovies(@QueryMap map: Map<String, String>): Observable<ApiResponse<Movie>>

    @GET("3/movie/{movieId}/videos")
    fun getTrailers(@Path("movieId") movieId: String): Observable<ApiResponse<Video>>

    @GET("3/movie/{movieId}/reviews")
    fun getReviews(@Path("movieId") movieId: String): Observable<ApiResponse<Review>>
}
