package com.example.movieapp.representation.details

import com.example.movieapp.data.model.WatchContent
import org.koin.core.logger.MESSAGE


sealed class DetailsState{

    data object  loading : DetailsState()
    data class Success(val content : WatchContent) : DetailsState()
    data class Error (val message: String) : DetailsState()
}