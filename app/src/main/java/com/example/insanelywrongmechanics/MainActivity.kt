package com.example.insanelywrongmechanics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun increment(view: View) {
        val textView = findViewById<TextView>(R.id.currentNumber)
        val countString = textView.text.toString()
        var count: Int = Integer.parseInt(countString)
        count++

        textView.text = count.toString()
    }

    fun genRandom(view: View) {
        // Create an Intent to start the second activity
        val randomIntent = Intent(this, SecondActivity::class.java)

        // Get the ID of the current number
        val textView = findViewById<TextView>(R.id.currentNumber)

        // Convert text content of textView in a string...
        val countString = textView.text.toString()

        // ...then as a number
        val count: Int = Integer.parseInt(countString)

        // Add the count to the extras for the Intent.
        randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)

        // Start the new activity.
        startActivity(randomIntent)
    }
}
