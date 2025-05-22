package com.example.cupcakery

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: android.content.SharedPreferences
    private lateinit var usernameText: TextView
    private lateinit var emailText: TextView
    private lateinit var passwordText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedPreferences = getSharedPreferences("CupcakePrefs", Context.MODE_PRIVATE)

        usernameText = findViewById(R.id.usernameTextView)
        emailText = findViewById(R.id.emailTextView)
        passwordText = findViewById(R.id.passwordTextView)
        val resetButton = findViewById<Button>(R.id.resetButton)
        val backButton = findViewById<Button>(R.id.backButton)

        loadProfileInfo()

        resetButton.setOnClickListener {
            showEditProfileDialog()
        }

        backButton.setOnClickListener {
            finish() // Go back to previous screen
        }
    }

    private fun loadProfileInfo() {
        val username = sharedPreferences.getString("username", "Not set")
        val email = sharedPreferences.getString("email", "Not set")
        val password = sharedPreferences.getString("password", "Not set")

        usernameText.text = "Name: $username"
        emailText.text = "Email: $email"
        passwordText.text = "Password: $password"
    }

    private fun showEditProfileDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_profile, null)

        val editUsername = dialogView.findViewById<EditText>(R.id.editUsername)
        val editEmail = dialogView.findViewById<EditText>(R.id.editEmail)
        val editPassword = dialogView.findViewById<EditText>(R.id.editPassword)

        // Pre-fill current values
        editUsername.setText(sharedPreferences.getString("username", ""))
        editEmail.setText(sharedPreferences.getString("email", ""))
        editPassword.setText(sharedPreferences.getString("password", ""))

        AlertDialog.Builder(this)
            .setTitle("Edit Profile Info")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val newUsername = editUsername.text.toString()
                val newEmail = editEmail.text.toString()
                val newPassword = editPassword.text.toString()

                sharedPreferences.edit()
                    .putString("username", newUsername)
                    .putString("email", newEmail)
                    .putString("password", newPassword)
                    .apply()

                loadProfileInfo()
                Toast.makeText(this, "Profile updated!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
