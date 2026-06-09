package com.example.revision

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }//End of ViewCompat

        //UI Elements
        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val edtUserMax = findViewById<EditText>(R.id.edtUserMax)
        val edtUserMin = findViewById<EditText>(R.id.edtUserMin)
        val edtRandomNumber = findViewById<EditText>(R.id.edtRandomNumber)
        val btnContinue = findViewById<Button>(R.id.btnContinue)

        // Button Click Listener
        btnContinue.setOnClickListener {
            Log.d("btnContinue", "Button pressed")

            // 1. Get current text values
            val username = edtUsername.text.toString()
            val minText = edtUserMin.text.toString()
            val maxText = edtUserMax.text.toString()

            // 2. Validation
            if (username.isEmpty()) {
                edtUsername.error = "Empty field"
                return@setOnClickListener
            }

            if (minText.isEmpty()) {
                edtUserMin.error = "Numeric field cannot be empty!"
                return@setOnClickListener
            }

            if (maxText.isEmpty()) {
                edtUserMax.error = "Numeric field cannot be empty"
                return@setOnClickListener
            }

            val userMin = minText.toIntOrNull()
            val userMax = maxText.toIntOrNull()

            if (userMin == null) {
                edtUserMin.error = "Numeric field must be a number"
                return@setOnClickListener
            }

            if (userMax == null) {
                edtUserMax.error = "Numeric field must be a number"
                return@setOnClickListener
            }

            if (userMin >= userMax) {
                edtUserMin.error = "Min must be less than Max"
                return@setOnClickListener
            }


            // 3. Generate and display random number
            val generatedNum = Random.nextInt(userMin, userMax + 1)
            edtRandomNumber.setText(generatedNum.toString())
            Log.d("MainActivity", "User: $username, Generated: $generatedNum")
        }
    }//End of onCreate
}
