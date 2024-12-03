package com.yashagozwan.cryptoapp.presentation.home

import android.util.Log
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

    private val _message = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    private val _topCoins = MutableLiveData<List<CoinModel>>(ArrayList())
    val topCoins: LiveData<List<CoinModel>> = _topCoins


    init {
        getTopCoins()
    }

    private fun getTopCoins() {
        val coins = cryptoRepository.getCoins()
        _topCoins.value = coins.take(5)
    }


    fun doInc() {
        _count.value = _count.value?.plus(1)
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}