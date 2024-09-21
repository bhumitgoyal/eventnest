package com.example.eventnest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.eventnest.R

class BefLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bef_login)
        val btnLoginStudent = findViewById<Button>(R.id.btnLoginStudent)
        val btnLoginOrganizer = findViewById<Button>(R.id.btnLoginOrganizer)
        btnLoginStudent.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnLoginOrganizer.setOnClickListener {
            val intent = Intent(this, OrgLoginActivity::class.java)
            startActivity(intent)
        }
    }
}