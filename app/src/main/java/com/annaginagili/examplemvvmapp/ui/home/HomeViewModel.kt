package com.annaginagili.examplemvvmapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaginagili.examplemvvmapp.data.request.RetrofitClient
import com.annaginagili.examplemvvmapp.data.response.MovieItem
import com.annaginagili.examplemvvmapp.utils.ResponseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class HomeViewModel: ViewModel() {
    var result: MutableLiveData<ResponseResult> = MutableLiveData()

    fun getMovies() {
        result.postValue(ResponseResult.Loading(true))
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getRetrofit().getMovies().enqueue(object : Callback<List<MovieItem>> {
                override fun onResponse(
                    call: Call<List<MovieItem>>,
                    response: Response<List<MovieItem>>
                ) {
                    Log.e("Result", response.body()?.size.toString())
                    result.postValue(ResponseResult.Success(response.body()))
                }

                override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {
                    result.postValue(ResponseResult.Error(t.stackTraceToString()))
                }
            })
        }
    }
}