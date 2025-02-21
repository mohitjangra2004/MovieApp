package com.example.movieapp.dependencyinjection

import com.example.movieapp.data.api.WatchModeApi
import com.facebook.shimmer.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {

        provideAuthInterceptor()
    }

    single {
        provideOkHttpClient(get())
    }

    single{

        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
        .addQueryParameter("api_key", "f242921626c54d736be0e63e08729a80")
        .build()
    val request = original.newBuilder()
        .url(url)
        .build()

    chain.proceed(request)

}

private fun provideOkHttpClient(authInterceptor: Interceptor)= OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .addInterceptor(HttpLoggingInterceptor().apply {

        level = if(BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY}
        else {
            HttpLoggingInterceptor.Level.NONE
        }
    })
    .connectTimeout(15 , TimeUnit.SECONDS)
    .writeTimeout(15 , TimeUnit.SECONDS)
    .readTimeout(15 , TimeUnit.SECONDS)
    .build()
