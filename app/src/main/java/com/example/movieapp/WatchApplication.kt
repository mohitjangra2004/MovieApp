package com.example.movieapp

import android.app.Application
import android.content.Context
import com.example.movieapp.dependencyinjection.appModule
import com.example.movieapp.dependencyinjection.networkModule
import com.example.movieapp.dependencyinjection.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WatchApplication : Application(){

    override fun onCreate() {
        super.onCreate()
            startKoin{
                    androidContext(this@WatchApplication)
                modules(appModule, networkModule, repositoryModule)
            }

        }

    }
