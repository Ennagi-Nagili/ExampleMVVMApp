package com.annaginagili.examplemvvmapp.data.request

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getRetrofit(): Api {
        val getMovies = Retrofit.Builder().baseUrl("https://tinyurl.com/").addConverterFactory(
        GsonConverterFactory.create()).build()
        return getMovies.create(Api::class.java)
    }

    fun getLoginRetrofit(): Api {
        val rtf = Retrofit.Builder().baseUrl("https://dummyjson.com/").addConverterFactory(
            GsonConverterFactory.create()).build()
        return rtf.create(Api::class.java)
    }
}