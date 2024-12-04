package com.yashagozwan.cryptoapp.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashagozwan.cryptoapp.core.utils.Utils
import com.yashagozwan.cryptoapp.databinding.CardSmallCoinBinding
import com.yashagozwan.cryptoapp.domain.model.CoinModel

class CoinsAdapter(
    private val coins: List<CoinModel>,
    private val onClick: (coin: CoinModel, view: View) -> Unit,
) : RecyclerView.Adapter<CoinsAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = CardSmallCoinBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = coins.size

    override fun onBindViewHolder(holder: ListViewHolder, index: Int) {
        val coin = coins[index]
        holder.binding.apply {
            Glide
                .with(imgCoin)
                .load(coin.image)
                .into(imgCoin)

            tvTitle.text = coin.name
            tvDescription.text = Utils.idr(coin.currentPrice)
            tvInfo.text = "${coin.priceChangePercentage24}%"

        }

        holder.itemView.setOnClickListener {
            onClick(coin, it)
        }
    }

    class ListViewHolder(val binding: CardSmallCoinBinding) : RecyclerView.ViewHolder(binding.root)
}