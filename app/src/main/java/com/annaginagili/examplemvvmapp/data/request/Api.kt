package com.annaginagili.examplemvvmapp.data.request

import com.annaginagili.examplemvvmapp.data.response.LoginResponse
import com.annaginagili.examplemvvmapp.data.response.MovieItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("pdd123123")
    fun getMovies(): Call<List<MovieItem>>

    @POST("auth/login")
    fun login(@Body credential: LoginData): Call<LoginResponse>
}