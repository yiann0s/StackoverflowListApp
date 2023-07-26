package com.example.myrncapp.networking

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

object ApiClient {

    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    private val BASE_URL = "https://api.stackexchange.com"


    val client: Retrofit
        get() {
            val mRetrofit: Retrofit.Builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().also { okhttp3Builder ->
                okhttp3Builder.addInterceptor(loggingInterceptor)
            }

            return mRetrofit.client(httpClient.build()).build()
        }

}