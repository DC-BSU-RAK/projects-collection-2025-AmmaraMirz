package com.example.cupcakery

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit

class EventsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("CupcakePrefs", MODE_PRIVATE)

        setupButton(R.id.imageButton, "Baby Shower")
        setupButton(R.id.imageButton2, "Birthday")
        setupButton(R.id.imageButton4, "Anniversary")
        setupButton(R.id.imageButton10, "Graduation")
        setupButton(R.id.imageButton11, "Eid")
        setupButton(R.id.imageButton12, "Wedding")
    }

    private fun setupButton(buttonId: Int, eventName: String) {
        findViewById<ImageButton>(buttonId).setOnClickListener {
            // Save selected event to SharedPreferences
            sharedPreferences.edit {
                putString("selected_event", eventName)
                apply()
            }

            // Navigate to FlavorActivity
            val intent = Intent(this, FlavorActivity::class.java)
            startActivity(intent)
        }
    }
}
