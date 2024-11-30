package com.yashagozwan.basicactivity

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var editLength: EditText
    private lateinit var editWidth: EditText
    private lateinit var editHeight: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // get widget or element by id
        editLength = findViewById(R.id.edit_length)
        editWidth = findViewById(R.id.edit_width)
        editHeight = findViewById(R.id.edit_height)
        buttonCalculate = findViewById(R.id.button_calculate)
        textViewResult = findViewById(R.id.text_view_result)

        // set action
        buttonCalculate.setOnClickListener(this)

        // recall state before
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            textViewResult.text = "Result: $result"
        }
    }


    private fun stringParseToDouble(editText: EditText) =
        editText.text.toString().trim().toDouble()

    private fun doValidate(editText: EditText): Boolean {
        if (editText.text.isEmpty()) {
            editText.error = "Field ini tidak boleh kosong"
            return false
        }
        return true
    }

    private fun doCalculate(view: View) {
        val validates = mutableListOf<Boolean>()

        if (doValidate(editLength)) validates.add(true)

        if (doValidate(editWidth)) validates.add(true)

        if (doValidate(editHeight)) validates.add(true)

        val resultValidate = validates.size == 3

        if (!resultValidate) return

        val length = stringParseToDouble(editLength)
        val width = stringParseToDouble(editWidth)
        val height = stringParseToDouble(editHeight)
        val result = length * width * height
        textViewResult.text = "Result: $result"


        Snackbar.make(view, "Success", Snackbar.LENGTH_SHORT).show()

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_calculate -> doCalculate(view)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, textViewResult.text.toString())
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }
}