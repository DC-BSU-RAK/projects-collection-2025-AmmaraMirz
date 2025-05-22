package com.example.cupcakery

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FlavorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flavor)

        // Handle system insets for full screen layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set click listeners for each flavor option
        setupFlavorSelection(R.id.chocolateButton, "Chocolate")
        setupFlavorSelection(R.id.caramelButton, "Caramel Drip")
        setupFlavorSelection(R.id.birthdayButton, "Birthday Special")
        setupFlavorSelection(R.id.mixberryButton, "Mixberry")
        setupFlavorSelection(R.id.lemonButton, "Lemon Zest")
        setupFlavorSelection(R.id.strawberryButton, "Strawberry")
    }

    private fun setupFlavorSelection(imageId: Int, flavorName: String) {
        val imageView = findViewById<ImageView>(imageId)
        imageView.setOnClickListener {
            val sharedPreferences = getSharedPreferences("CupcakePrefs", MODE_PRIVATE)

            // Save selected flavor
            sharedPreferences.edit().putString("selected_flavor", flavorName).apply()

            // Get saved event (if any)
            val eventName = sharedPreferences.getString("EVENT", "No event")

            // Navigate to result screen with both values
            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("FLAVOR", flavorName)
                putExtra("EVENT", eventName)
            }
            startActivity(intent)
            finish()
        }
    }
}