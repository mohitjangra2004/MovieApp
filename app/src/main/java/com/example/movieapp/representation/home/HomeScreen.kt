package com.example.movieapp.representation.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import com.example.movieapp.data.model.WatchContent
import com.example.movieapp.representation.home.components.*
import com.example.movieapp.utils.ContentType
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onNavigateToDetails: (String) -> Unit
) {
    val state by viewModel.state.collectAsState() 
    val contentType by viewModel.contentType.collectAsState()


    Column {

        HomeTopBar(selectedType = contentType, onTypeSelected = viewModel::setContentType)

        AnimatedContent(targetState = state) { currentState ->
            when (currentState) {  
                is HomeState.Loading -> 
                    ShimmerEffect()
                }
                is HomeState.Success -> {
                
                    val content: List<WatchContent> = when (contentType) {
                        ContentType.MOVIES -> currentState.movies
                        ContentType.TV_SHOWS -> currentState.tvShows
                    }

                    ContentGrid(
                        contents = content,
                        onItemClick = onNavigateToDetails
                    )
                }
                is HomeState.Error -> {
                    ErrorContent(
                        message = currentState.message,
                        onRetry = viewModel::loadContent
                    )
                }
            }
        }
    }
}
