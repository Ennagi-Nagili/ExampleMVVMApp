package com.annaginagili.examplemvvmapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.annaginagili.examplemvvmapp.R
import com.annaginagili.examplemvvmapp.data.response.MovieItem
import com.annaginagili.examplemvvmapp.databinding.ActivityHomeBinding
import com.annaginagili.examplemvvmapp.utils.ResponseResult

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getMovies()

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.result.observe(this) {
            when (it) {
                is ResponseResult.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
                is ResponseResult.Error -> {

                }
                is ResponseResult.Success<*> -> {
                    binding.progress.visibility = View.INVISIBLE
                    binding.tvTitle.text = (it.data as List<MovieItem>)[0].Title
                }
            }
        }
    }
}