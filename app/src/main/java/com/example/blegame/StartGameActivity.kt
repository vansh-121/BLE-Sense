@file:Suppress("DEPRECATION")

package com.example.blegame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide

class StartGameActivity : AppCompatActivity() {

    private lateinit var bleAdapter: BluetoothAdapterWrapper
    private val PERMISSION_REQUEST_CODE = 1001

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startgame)

        bleAdapter = BluetoothAdapterWrapper(this)

        val startGameButton: ImageView = findViewById(R.id.startGameButton)

        // Load the GIF from the assets folder
        Glide.with(this)
            .asGif()
            .load("file:///android_asset/START GAME.gif")
            .into(startGameButton)

        // Set an onClickListener for the ImageView
        startGameButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }

        requestPermissions()
        bleAdapter.checkAndEnableBluetooth()

        if (!isLocationEnabled()) {
            promptEnableLocation()
        }
    }

    private fun requestPermissions() {
        val permissions = bleAdapter.getRequiredPermissions()
        val ungrantedPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }
        if (ungrantedPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, ungrantedPermissions.toTypedArray(), PERMISSION_REQUEST_CODE)
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun promptEnableLocation() {
        AlertDialog.Builder(this)
            .setTitle("Enable Location")
            .setMessage("Location services are required to scan for nearby devices. Please enable location services.")
            .setPositiveButton("Enable") { _, _ ->
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(this, "Location is required for Bluetooth scanning", Toast.LENGTH_SHORT).show()
            }
            .create()
            .show()
    }
}
