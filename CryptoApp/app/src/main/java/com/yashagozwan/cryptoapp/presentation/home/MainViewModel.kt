package com.yashagozwan.cryptoapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yashagozwan.cryptoapp.domain.model.CoinModel
import com.yashagozwan.cryptoapp.domain.repository.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository,
) : ViewModel() {
    private val _coin = MutableLiveData<CoinModel>()
    val coin: LiveData<CoinModel> = _coin

    private val _topCoins = MutableLiveData<List<CoinModel>>(listOf())
    val topCoins: LiveData<List<CoinModel>> = _topCoins

    private val _coins = MutableLiveData<List<CoinModel>>(listOf())
    val coins: LiveData<List<CoinModel>> = _coins

    init {
        getCoins()
    }

    private fun getCoins() {
        val coins = cryptoRepository.getCoins()

        _coin.value = coins.first()
        _topCoins.value = coins.take(5)
        _coins.value = coins.subList(5, coins.size - 1).take(5)
    }

    fun setCoin(coin: CoinModel) {
        _coin.value = coin
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}