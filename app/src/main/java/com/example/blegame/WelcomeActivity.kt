package com.example.blegame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val singlePlayerButton: ImageView = findViewById(R.id.singlePlayerButton)
        // Load the GIF from the assets folder
        Glide.with(this)
            .asGif()
            .load("file:///android_asset/Single_Player.gif")
            .into(singlePlayerButton)

        val multiPlayerButton: ImageView = findViewById(R.id.multiPlayerButton)
        // Load the GIF from the assets folder
        Glide.with(this)
            .asGif()
            .load("file:///android_asset/Multi_Player.gif")
            .into(multiPlayerButton)

        val backButton: ImageButton = findViewById(R.id.backButton)

        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }


        singlePlayerButton.setOnClickListener {
            val intent = Intent(this, GameDeviceListActivity::class.java)
            intent.putExtra("mode", "single")
            startActivity(intent)
        }

        multiPlayerButton.setOnClickListener {
            val intent = Intent(this, MultiplayerActivity::class.java)
            intent.putExtra("mode", "multi")
            startActivity(intent)
        }
    }
}
