package com.example.cupcakery

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Get saved data from SharedPreferences
        val sharedPreferences = getSharedPreferences("CupcakePrefs", MODE_PRIVATE)
        val flavor = sharedPreferences.getString("selected_flavor", "No flavor") ?: "No flavor"
        val event = sharedPreferences.getString("selected_event", "No event") ?: "No event"

        // Add to history
        OrderHistory.orderList.add(Pair(flavor, event))

        // Find views
        val flavorTextView: TextView = findViewById(R.id.flavorText)
        val eventTextView: TextView = findViewById(R.id.eventText)
        val funCommentTextView: TextView = findViewById(R.id.funCommentText)

        // Set flavor and event text
        flavorTextView.text = "You picked: $flavor"
        eventTextView.text = "For: $event"

        // Comment based on flavor
        val funComment = when (flavor.lowercase()) {
            "chocolate" -> "Classic and comforting — a true crowd-pleaser!"
            "red velvet" -> "Elegant and rich — perfect for love stories!"
            "strawberry" -> "Sweet and fresh — just like a spring fling!"
            "lemon zest" -> "Zesty and bright — for unforgettable moments!"
            "caramel drip" -> "Smooth, indulgent, and totally unforgettable!"
            "birthday special" -> "Let the celebration begin with every bite!"
            "mixberry" -> "Bursting with flavor — just like you!"
            else -> "A delightful choice — yum!"
        }
        funCommentTextView.text = funComment

        // Navigation buttons
        findViewById<Button>(R.id.homeButton).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.restartButton).setOnClickListener {
            startActivity(Intent(this, FlavorActivity::class.java))
            finish()
        }
    }
}