package com.yashagozwan.cryptoapp.presentation.home

import android.R
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.yashagozwan.cryptoapp.core.adapter.TopCoinAdapter
import com.yashagozwan.cryptoapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildTopCoins()


        // Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = window
            window.statusBarColor = ContextCompat.getColor(this, R.color.darker_gray)

            // Set light icons on the status bar for better visibility on light backgrounds
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val window = window
            window.navigationBarColor = ContextCompat.getColor(this, R.color.darker_gray)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }


    private fun buildTopCoins() {
        binding.includeRvTopCoins.rvTopCoins.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.topCoins.observe(this@MainActivity) {
            val adapter = TopCoinAdapter(it) {
                Toast
                    .makeText(this@MainActivity, it.id, Toast.LENGTH_SHORT)
                    .show()
            }

            binding.includeRvTopCoins.rvTopCoins.adapter = adapter
        }
    }
}