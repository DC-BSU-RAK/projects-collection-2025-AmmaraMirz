package com.example.cupcakery

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class OrderHistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val historyTextView: TextView = findViewById(R.id.orderHistoryText)
        val backButton: Button = findViewById(R.id.backButton)

        val historyText = StringBuilder()

        if (OrderHistory.orderList.isEmpty()) {
            historyText.append("No orders yet.")
        } else {
            OrderHistory.orderList.forEachIndexed { index, pair ->
                historyText.append("Order ${index + 1}: ${pair.first} for ${pair.second}\n")
            }
        }

        historyTextView.text = historyText.toString()

        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }
    }
}
