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
    val state by viewModel.state.collectAsState() // ✅ HomeState variable
    val contentType by viewModel.contentType.collectAsState() // ✅ ContentType variable

    Column {
        // ✅ Correct usage of ContentType
        HomeTopBar(selectedType = contentType, onTypeSelected = viewModel::setContentType)

        AnimatedContent(targetState = state) { currentState ->
            when (currentState) {  // ✅ HomeState comparison here
                is HomeState.Loading -> {
                    ShimmerEffect()
                }
                is HomeState.Success -> {
                    // ✅ Correct usage of ContentType
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
