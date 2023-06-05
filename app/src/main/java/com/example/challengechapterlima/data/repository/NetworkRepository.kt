package com.example.challengechapterlima.data.repository

import com.example.challengechapterlima.data.remote.ApiService
import com.example.challengechapterlima.data.remote.model.DetailMovieResponse
import com.example.challengechapterlima.data.remote.model.MovieResponse
import javax.inject.Inject


interface NetworkRepository {

    suspend fun getAllMoviesNowPlaying(
        page: Int = 1
    ): MovieResponse

    suspend fun getDetailMovie(
        movieId: Int
    ): DetailMovieResponse
}

class NetworkRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NetworkRepository {
    override suspend fun getAllMoviesNowPlaying(page: Int): MovieResponse =
        apiService.getAllMoviesNowPlaying()

    override suspend fun getDetailMovie(movieId: Int): DetailMovieResponse =
        apiService.getDetailMovie(movieId)

}