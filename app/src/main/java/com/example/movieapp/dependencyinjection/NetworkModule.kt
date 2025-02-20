package com.example.movieapp.dependencyinjection

import com.example.movieapp.data.api.WatchModeApi
import okhttp3.Interceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single {

        provideAuthInterceptor()
    }

    single {
        provideOkHyypClient(get())
    }

    single{

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(WatchModeApi::class.java)
    }
}


private fun provideAuthInterceptor()=Interceptor{chain ->
    val original =chain.request()
    val originalURL= original.url

    val url=originalURL.newBuilder()
        .addQueryParameter("api_key", "c60f4ddbe8e3035af9697df1f414a14f")
        .build()
    val request = original.newBuilder()
        .url(url)
        .build()

    chain.proceed(request)

}

