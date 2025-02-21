package com.example.movieapp.data.model.response

data class WatchContentListResponse(

    val page : String,
    val result: List<WatchContentResponse> ,
    val total_pages:Int ,
    val total_result:Int
)
