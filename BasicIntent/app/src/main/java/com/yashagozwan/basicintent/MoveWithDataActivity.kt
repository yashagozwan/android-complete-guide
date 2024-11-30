package com.yashagozwan.basicintent

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveWithDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_data)

        val tvDataReceived = findViewById<TextView>(R.id.tv_data_received)
        val age = intent.getIntExtra(EXTRA_AGE, 0)
        val name = intent.getStringExtra(EXTRA_NAME)
        val text = "Name: $name, Age: $age"
        tvDataReceived.text = text

    }

    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_NAME = "extra_name"
    }
}