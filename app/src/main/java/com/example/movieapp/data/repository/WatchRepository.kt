package com.example.movieapp.data.repository

import com.example.movieapp.data.model.WatchContent
import io.reactivex.rxjava3.core.Single

interface WatchRepository {

    fun getMoviesAndTvShows():Single<Pair<List<WatchContent> , List<WatchContent>>>
    suspend fun getContentDetails(
        contentId:String ,
        isMovie:Boolean,
    ):WatchContent
}