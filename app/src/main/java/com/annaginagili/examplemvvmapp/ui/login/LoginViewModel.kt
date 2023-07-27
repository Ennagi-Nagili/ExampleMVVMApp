package com.annaginagili.examplemvvmapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.annaginagili.examplemvvmapp.data.request.RetrofitClient
import com.annaginagili.examplemvvmapp.data.request.LoginData
import com.annaginagili.examplemvvmapp.data.response.LoginResponse
import com.annaginagili.examplemvvmapp.data.response.MovieItem
import com.annaginagili.examplemvvmapp.utils.ResponseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    var result: MutableLiveData<ResponseResult> = MutableLiveData()

    fun login(username: String, password: String) {
        result.postValue(ResponseResult.Loading(true))
        CoroutineScope(Dispatchers.IO).launch {
            RetrofitClient.getLoginRetrofit().login(LoginData(username, password))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.e("Result", response.body()?.firstName.toString())
                        result.postValue(ResponseResult.Success(response.body()))
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        result.postValue(ResponseResult.Error(t.stackTraceToString()))
                    }
                })
        }
    }
}