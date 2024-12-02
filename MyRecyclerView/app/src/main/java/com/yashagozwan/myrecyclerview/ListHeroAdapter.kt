package com.yashagozwan.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yashagozwan.myrecyclerview.databinding.ItemRowHeroBinding

class ListHeroAdapter(
    private val heroes: List<HeroModel>,
) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {
    private lateinit var actions: Actions

    fun setOnClick(actions: Actions) {
        this.actions = actions
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {

        val binding = ItemRowHeroBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ListViewHolder(binding)
    }

    override fun getItemCount() = heroes.size

    override fun onBindViewHolder(holder: ListViewHolder, index: Int) {
        val hero = heroes[index]

        Glide.with(holder.itemView)
            .load(hero.photo)
            .into(holder.binding.imgItemPhoto)

        holder.binding.apply {
            tvItemName.text = hero.name
            tvItemDescription.text = hero.description
        }

        holder.itemView.setOnClickListener {
            actions.onClick(hero, it)
        }
    }

    inner class ListViewHolder(val binding: ItemRowHeroBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Actions {
        fun onClick(hero: HeroModel, view: View)
    }
}