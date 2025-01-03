package com.example.blegame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitymain)


        // CardView for BLE App
        val cardBLEApp: CardView = findViewById(R.id.cardBLEApp)
        val btnBLE: Button = findViewById(R.id.btnBLE)
        btnBLE.setOnClickListener {
            Toast.makeText(this, "Navigating to BLE App", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BLEAppActivity::class.java)
            startActivity(intent)
        }

        // CardView for Games Section
        val cardGames: CardView = findViewById(R.id.cardGames)
        val btnGames: Button = findViewById(R.id.btnGames)
        btnGames.setOnClickListener {
            Toast.makeText(this, "Navigating to Games Section", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StartGameActivity::class.java)
            startActivity(intent)
        }
    }
}
