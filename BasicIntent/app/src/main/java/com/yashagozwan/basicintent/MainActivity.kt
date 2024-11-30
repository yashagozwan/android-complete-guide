package com.yashagozwan.basicintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_move_activity).setOnClickListener(this)

        findViewById<Button>(R.id.btn_move_activity_data).setOnClickListener(this)

        findViewById<Button>(R.id.btn_move_activity_object).setOnClickListener(this)

        findViewById<Button>(R.id.btn_dial_number).setOnClickListener(this)

        findViewById<Button>(R.id.btn_move_for_result).setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)
    }

    private fun moveActivity(view: View) {
        Intent(this@MainActivity, MoveActivity::class.java)
            .also(::startActivity)

        Toast.makeText(this, "Activity Move", Toast.LENGTH_SHORT).show()
    }

    private fun moveActivityData(view: View) {
        Intent(this@MainActivity, MoveWithDataActivity::class.java).apply {
            putExtra(MoveWithDataActivity.EXTRA_AGE, 28)
            putExtra(MoveWithDataActivity.EXTRA_NAME, "Yasha Gozwan Shuhada")
        }.also(::startActivity)
    }


    private fun moveActivityWithObject() {
        val person = Person(
            name = "Yasha Gozwan Shuahda", age = 18, email = "yasha@gmail.com", city = "Depok"
        )

        Intent(this@MainActivity, MoveWithObjectActivity::class.java).apply {
            putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
        }.also(::startActivity)

    }

    private fun doDialPhone() {
        val phone = "0813618327373"
        Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            .also(::startActivity)
    }


    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == MoveForResultActivity.RESULT_CODE && it.data != null) {
            val selectedValue =
                it.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)

            tvResult.text = "Hasil: $selectedValue"
        }
    }


    private fun doChooseNumber() {
        Intent(this, MoveForResultActivity::class.java).also {
            resultLauncher.launch(it)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_move_activity -> moveActivity(view = view)
            R.id.btn_move_activity_data -> moveActivityData(view = view)
            R.id.btn_move_activity_object -> moveActivityWithObject()
            R.id.btn_dial_number -> doDialPhone()
            R.id.btn_move_for_result -> doChooseNumber()
        }
    }
}