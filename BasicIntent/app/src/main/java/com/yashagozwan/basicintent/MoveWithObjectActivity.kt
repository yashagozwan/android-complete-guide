package com.yashagozwan.basicintent

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveWithObjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val tvObject = findViewById<TextView>(R.id.tv_object_received)

        val person = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PERSON, Person::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PERSON)
        }

        if (person != null) {
            val text = StringBuilder()
                .append("Name: ${person.name}")
                .append("\n")
                .append("Email: ${person.email}")
                .append("\n")
                .append("Age: ${person.age}")
                .append("\n")
                .append("City: ${person.city}")
                .append("\n")
                .toString()

            tvObject.text = text
        }
    }

    companion object {
        const val EXTRA_PERSON = "extra_person"
    }
}