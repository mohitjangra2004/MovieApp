package com.example.movieapp.data.repository

import com.example.movieapp.data.api.WatchModeApi
import com.example.movieapp.data.mapper.WatchMapper
import com.example.movieapp.data.model.WatchContent
import io.reactivex.rxjava3.core.Single

class WatchRepositoryImplement (

    private  val api : WatchModeApi,
    private val mapper : WatchMapper
) : WatchRepository {
    override fun getMoviesAndTvShows(): Single<Pair<List<WatchContent>, List<WatchContent>>> {
        return Single.zip(
            api.getMovies()
                .map { mapper.mapResponseListToWatchContent(it.result) },
            api.getTvShows()
                .map { mapper.mapResponseListToWatchContent(it.result) }
        ) {
          movies , tvShows ->
          Pair(movies , tvShows)

        }
    }

    override suspend fun getContentDetails(contentId: String, isMovie: Boolean): WatchContent {
        return if (isMovie){
            mapper.mapResponseToWatchContent(api.getMovieDetails(contentId.toInt()))
        }

        else {
            mapper.mapResponseToWatchContent(api.getTvShowDetails(contentId.toInt()))
        }
    }
}
