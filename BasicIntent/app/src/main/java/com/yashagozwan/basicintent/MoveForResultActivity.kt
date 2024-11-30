package com.yashagozwan.basicintent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MoveForResultActivity : AppCompatActivity(), OnClickListener {

    private lateinit var btnChoose: Button
    private lateinit var rgNumber: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_for_result)

        btnChoose = findViewById(R.id.btn_choose)
        rgNumber = findViewById(R.id.rg_number)

        btnChoose.setOnClickListener(this)

    }


    private fun doSelectNumber() {

        if (rgNumber.checkedRadioButtonId > 0) {
            var value = 0
            when (rgNumber.checkedRadioButtonId) {
                R.id.rb_50 -> value = 50
                R.id.rb_100 -> value = 100
                R.id.rb_150 -> value = 150
                R.id.rb_200 -> value = 200
            }

            Intent().apply {
                putExtra(EXTRA_SELECTED_VALUE, value)
            }.also {
                setResult(RESULT_CODE, it)
                finish()
            }

        }
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_choose -> doSelectNumber()
        }
    }

    companion object {
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
        const val RESULT_CODE = 110
    }
}