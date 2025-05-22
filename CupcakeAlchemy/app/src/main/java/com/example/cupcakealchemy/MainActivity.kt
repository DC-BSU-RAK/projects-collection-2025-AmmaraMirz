package com.example.cupcakealchemy

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val eventSpinner: Spinner = findViewById(R.id.eventSpinner)
        val flavourSpinner: Spinner = findViewById(R.id.flavourSpinner)

        val events = arrayOf("Birthday", "Wedding", "Anniversary", "Eid")
        val flavours = arrayOf("Strawberry", "Red Velvet", "Blueberry", "Chocolate")

        eventSpinner.adapter = ArrayAdapter (this@MainActivity, android.R.layout.simple_spinner_dropdown_item, events)
        flavourSpinner.adapter = ArrayAdapter (this@MainActivity, android.R.layout.simple_spinner_dropdown_item, flavours)

        val mapped_cupcake = mapOf(
          "Birthday" to mapOf(
              "Strawberry" to "A Strawberry Surprise is perfect for a sweet birthday bash",
              "Red Velvet" to "Celebrate with a Red Velvet Dream—rich, bold, and unforgettable",
              "Blueberry" to "Make it fruity A Blueberry Pop cupcake fits the party mood",
              "Chocolate" to "You can never go wrong with a Chocolate Bliss birthday treat"
          ),
          "Wedding" to mapOf(
              "Strawberry" to "Strawberry Elegance—light, romantic, and picture-perfect",
              "Red Velvet" to "Red Velvet Romance, made for magical wedding moments",
              "Blueberry" to "Serve up Blueberry Lace—something blue, something sweet!",
              "Chocolate" to "Chocolate Devotion: rich flavor for richer memories"
          ),
          "Anniversary" to mapOf(
              "Strawberry" to "Toast your love with Strawberry Passion!",
              "Red Velvet" to "Red Velvet Heartbeat—timeless love in every bite",
              "Blueberry" to "Blueberry Harmony, because sweet memories deserve a sweet treat",
              "Chocolate" to "Chocolate Affection—bittersweet and beautiful."
          ),
          "Eid" to mapOf(
              "Strawberry" to "Celebrate Eid with Strawberry Delight—it’s festive and fresh!",
              "Red Velvet" to "Eid Velvet—luxurious and perfect for joyful gatherings.",
              "Blueberry" to "A Blueberry Eid Bloom to sweeten your day",
              "Chocolate" to "Chocolate Crescent—indulgent and Eid-ready!"
          )
        )

        val ImagesCupcake = mapOf(
            "A Strawberry Surprise is perfect for a sweet birthday bash" to R.drawable.strawberrycupcake,
            "Celebrate with a Red Velvet Dream—rich, bold, and unforgettable" to R.drawable.redvelvetcupcake,
            "Make it fruity A Blueberry Pop cupcake fits the party mood" to R.drawable.blueberrycupcake,
            "You can never go wrong with a Chocolate Bliss birthday treat" to R.drawable.chocolatecupcake,

            "Strawberry Elegance—light, romantic, and picture-perfect" to R.drawable.strawberrycupcake,
            "Red Velvet Romance, made for magical wedding moments" to R.drawable.redvelvetcupcake,
            "Serve up Blueberry Lace—something blue, something sweet!" to R.drawable.blueberrycupcake,
            "Chocolate Devotion: rich flavor for richer memories" to R.drawable.chocolatecupcake,

            "Toast your love with Strawberry Passion!" to R.drawable.strawberrycupcake,
            "Red Velvet Heartbeat—timeless love in every bite" to R.drawable.redvelvetcupcake,
            "Blueberry Harmony, because sweet memories deserve a sweet treat" to R.drawable.blueberrycupcake,
            "Chocolate Affection—bittersweet and beautiful." to R.drawable.chocolatecupcake,

            "Celebrate Eid with Strawberry Delight—it’s festive and fresh!" to R.drawable.strawberrycupcake,
            "Eid Velvet—luxurious and perfect for joyful gatherings." to R.drawable.redvelvetcupcake,
            "A Blueberry Eid Bloom to sweeten your day" to R.drawable.blueberrycupcake,
            "Chocolate Crescent—indulgent and Eid-ready!" to R.drawable.chocolatecupcake
        )

        val instructButton: Button = findViewById(R.id.instructButton)
        instructButton.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_instruct_pop_up, null)
            val width = 1025
            val height = 1875
            val instructWindow = PopupWindow(popupView, width, height, true)
            instructWindow.showAtLocation(popupView, Gravity.BOTTOM, 0, 75)
        }

            val bakeButton: Button = findViewById(R.id.bakeButton)
        bakeButton.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.activity_cupcake_pop_up, null)
            val width = 1050
            val height = 950
            val bakeWindow = PopupWindow(popupView, width, height, true)
            bakeWindow.showAtLocation(popupView, Gravity.BOTTOM, 3, 75)

            val selectedEvent = eventSpinner.selectedItem.toString()
            val selectedFlavour = flavourSpinner.selectedItem.toString()

            val result_cupcake = mapped_cupcake[selectedEvent]?.get(selectedFlavour)
                ?: "Invalid selection! Select your preferred choice."
            val Cupcake: TextView = popupView.findViewById(R.id.Cupcake)
            Cupcake.text = result_cupcake

            val ImageCupcake: ImageView = popupView.findViewById(R.id.ImageCupcake)
            val imageRes = ImagesCupcake[result_cupcake] ?: R.drawable.strawberrycupcake
            ImageCupcake.setImageResource(imageRes)
        }
    }
}
