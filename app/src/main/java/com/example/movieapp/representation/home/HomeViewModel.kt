package com.example.movieapp.representation.home


import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.usecase.GetMoviesAndTvShowsUseCase

class HomeViewModel(private val getMoviesAndTvShowsUseCase: GetMoviesAndTvShowsUseCase) : ViewModel() {
}