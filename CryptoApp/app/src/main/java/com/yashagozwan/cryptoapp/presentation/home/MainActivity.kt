package com.yashagozwan.cryptoapp.presentation.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yashagozwan.cryptoapp.R
import com.yashagozwan.cryptoapp.core.adapter.CoinsAdapter
import com.yashagozwan.cryptoapp.core.adapter.TopCoinAdapter
import com.yashagozwan.cryptoapp.core.utils.Utils
import com.yashagozwan.cryptoapp.databinding.ActivityMainBinding
import com.yashagozwan.cryptoapp.presentation.coins.CoinsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarAndSystemNavigationBar()
        buildCoin()
        buildTopCoins()
        buildCoins()
        buildMoreButton()
    }

    private fun buildMoreButton() {
        binding.tvMore.setOnClickListener {
            Intent(it.context, CoinsActivity::class.java).also(::startActivity)
        }
    }


    private fun setStatusBarAndSystemNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(R.color.light_grey)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.navigationBarColor = resources.getColor(R.color.light_grey)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }


    private fun buildCoin() {
        viewModel.coin.observe(this) { coin ->
            Glide
                .with(binding.largeImgIcon)
                .load(coin.image)
                .into(binding.largeImgIcon)
            binding.largeTvTitle.text = "${coin.athChangePercentage}"
            binding.largeTvInfo.text = "${coin.atl}"
            binding.largeTvPrice.text = Utils.idr(coin.currentPrice)
        }
    }


    private fun buildTopCoins() {
        binding.rvTopCoins.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.topCoins.observe(this@MainActivity) { coins ->
            val adapter = TopCoinAdapter(coins) { coin ->
                viewModel.setCoin(coin)
            }

            binding.rvTopCoins.adapter = adapter
        }
    }


    private fun buildCoins() {
        binding.rvCoins.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        viewModel.coins.observe(this) { coins ->
            val adapter = CoinsAdapter(coins) { coin, view ->
                viewModel.setCoin(coin)
            }

            binding.rvCoins.adapter = adapter
        }
    }
}