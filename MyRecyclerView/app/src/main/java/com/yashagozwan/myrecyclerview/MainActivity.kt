package com.yashagozwan.myrecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yashagozwan.myrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var rvHeroes: RecyclerView
    private val heroes = ArrayList<HeroModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        rvHeroes = binding.rvHeroes.apply { setHasFixedSize(true) }

        heroes.addAll(getHeroes())

        showHeroes()
    }


    private fun getHeroes(): List<HeroModel> {

        val names = resources.getStringArray(R.array.data_name)
        val descriptions = resources.getStringArray(R.array.data_description)
        val images = resources.getStringArray(R.array.data_photo_url)

        for (index in names.indices) {
            val hero = HeroModel(
                name = names[index],
                description = descriptions[index],
                photo = images[index]
            )

            heroes.add(hero)
        }

        return heroes
    }

    private fun showHeroes() {
        rvHeroes.layoutManager = LinearLayoutManager(this)

        ListHeroAdapter(heroes).apply {
            setOnClick(object : ListHeroAdapter.Actions {
                override fun onClick(hero: HeroModel, view: View) {
                    Snackbar
                        .make(view, hero.name, Snackbar.LENGTH_SHORT)
                        .show()
                }
            })

            rvHeroes.adapter = this
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun doListAction() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
    }

    private fun doGridAction() {
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> doListAction()
            R.id.action_grid -> doGridAction()
        }

        return super.onOptionsItemSelected(item)
    }
}