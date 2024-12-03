package com.yashagozwan.cryptoapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashagozwan.cryptoapp.core.utils.Utils
import com.yashagozwan.cryptoapp.databinding.CardMediumCoinBinding
import com.yashagozwan.cryptoapp.databinding.TopCoinsBinding
import com.yashagozwan.cryptoapp.domain.model.CoinModel

class TopCoinAdapter(
    private val coins: List<CoinModel>,
    private val onClick: (coin: CoinModel) -> Unit,
) : RecyclerView.Adapter<TopCoinAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            CardMediumCoinBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = coins.size

    override fun onBindViewHolder(holder: ListViewHolder, index: Int) {
        val coin = coins[index]

        holder.binding.apply {
            Glide
                .with(mediumCoinImage)
                .load(coin.image)
                .into(mediumCoinImage)

            mediumTvInfo.text = "${coin.marketCapChangePercentage24}"
            mediumTvTitle.text = "${coin.priceChangePercentage24}%"
            mediumTvDescription.text = "${coin.symbol.uppercase()} ${Utils.idr(coin.currentPrice)}"
        }

        holder.itemView.setOnClickListener { onClick(coin) }
    }


    class ListViewHolder(val binding: CardMediumCoinBinding) : RecyclerView.ViewHolder(binding.root)

}