package com.annaginagili.examplemvvmapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.annaginagili.examplemvvmapp.R
import com.annaginagili.examplemvvmapp.data.response.LoginResponse
import com.annaginagili.examplemvvmapp.data.response.MovieItem
import com.annaginagili.examplemvvmapp.databinding.ActivityLoginBinding
import com.annaginagili.examplemvvmapp.ui.home.HomeViewModel
import com.annaginagili.examplemvvmapp.utils.ResponseResult

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var submit: Button
    lateinit var viewModel: LoginViewModel
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        username = binding.username
        password = binding.password
        submit = binding.submit
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        result = binding.result

        submit.setOnClickListener {
            viewModel.login(username.text.toString(), password.text.toString())
        }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.result.observe(this) {
            when (it) {
                is ResponseResult.Loading -> {

                }
                is ResponseResult.Error -> {

                }
                is ResponseResult.Success<*> -> {
                    result.text = (it.data as LoginResponse).firstName + " " + (it.data as LoginResponse).lastName
                }
            }
        }
    }
}