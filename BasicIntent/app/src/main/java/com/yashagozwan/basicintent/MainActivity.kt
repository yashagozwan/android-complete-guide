package com.yashagozwan.basicintent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_move_activity)
            .setOnClickListener(this)

        findViewById<Button>(R.id.btn_move_activity_data)
            .setOnClickListener(this)
    }

    private fun moveActivity(view: View) {
        Intent(this@MainActivity, MoveActivity::class.java)
            .also { startActivity(it) }

        Toast
            .makeText(this, "Activity Move", Toast.LENGTH_SHORT)
            .show()
    }

    private fun moveActivityData(view: View) {
        Intent(this@MainActivity, MoveWithDataActivity::class.java)
            .apply {
                putExtra(MoveWithDataActivity.EXTRA_AGE, 28)
                putExtra(MoveWithDataActivity.EXTRA_NAME, "Yasha Gozwan Shuhada")
            }
            .also {
                startActivity(it)
            }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_move_activity -> moveActivity(view = view)
            R.id.btn_move_activity_data -> moveActivityData(view = view)
        }
    }
}