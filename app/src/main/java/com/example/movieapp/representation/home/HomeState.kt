package com.example.movieapp.representation.home

import com.example.movieapp.data.model.WatchContent

sealed class HomeState {

    object loading : HomeState()
    data class Success (
        val movies:List<WatchContent>,
        val tvShows: List<WatchContent>
    ) : HomeState()
    data class  Error(val message : String) : HomeState()
}

