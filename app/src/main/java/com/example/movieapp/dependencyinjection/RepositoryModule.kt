package com.example.movieapp.dependencyinjection

import com.example.movieapp.data.mapper.WatchMapper
import com.example.movieapp.data.repository.WatchRepository
import com.example.movieapp.data.repository.WatchRepositoryImplement
import org.koin.dsl.module

val repositoryModule = module {
    single { WatchMapper() }
    single <WatchRepository> {WatchRepositoryImplement(get() ,get())}
}