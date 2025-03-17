package com.example.lab11

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactButton: MaterialButton = findViewById(R.id.contactButton)
        contactButton.setOnClickListener {
            showContactOptions()
        }
    }

    private fun showContactOptions() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Contact Options")
            .setItems(arrayOf("Email", "LinkedIn", "Phone")) { _, which ->
                when (which) {
                    0 -> sendEmail()
                    1 -> openLinkedIn()
                    2 -> dialPhone()
                }
            }
            .show()
    }

    private fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:21522247@ms.uit.edu.vn")
            putExtra(Intent.EXTRA_SUBJECT, "Professional Inquiry")
        }
        startActivity(Intent.createChooser(intent, "Send Email"))
    }

    private fun openLinkedIn() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://linkedin.com/in/Kijin"))
        startActivity(intent)
    }

    private fun dialPhone() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:+84xxxxxxx")
        }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareCV()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareCV() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out my professional profile: https://linkedin.com/in/Kijin")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share CV"))
    }
}
