package com.example.movieapp.dependencyinjection

import com.example.movieapp.WatchApplication
import com.example.movieapp.domain.usecase.GetContentDetailsUseCase
import com.example.movieapp.domain.usecase.GetMoviesAndTvShowsUseCase
import com.example.movieapp.representation.details.DetailsViewModel
import com.example.movieapp.representation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { WatchApplication() }

    factory { GetContentDetailsUseCase(get()) }
    factory { GetMoviesAndTvShowsUseCase(get()) }

   viewModel { HomeViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}





