package com.yashagozwan.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListHeroAdapter(
    private val heroes: List<HeroModel>,
) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var actions: Actions

    fun setOnClick(actions: Actions) {
        this.actions = actions
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)

        return ListViewHolder(view)
    }

    override fun getItemCount() = heroes.size

    override fun onBindViewHolder(holder: ListViewHolder, index: Int) {
        val hero = heroes[index]

        holder.apply {
            imageItemPhoto.setImageResource(hero.photo)
            tvItemName.text = hero.name
            tvItemDescription.text = hero.description

            itemView.setOnClickListener {
                actions.onClick(hero = hero, view = it)
            }
        }
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageItemPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvItemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvItemDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    interface Actions {
        fun onClick(hero: HeroModel, view: View)
    }
}