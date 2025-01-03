package com.example.blegame

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class GameplayActivity : AppCompatActivity() {

    private lateinit var bleAdapter: BluetoothAdapterWrapper
    private var isScanning = false
    private lateinit var deviceNameTextView: TextView
    private lateinit var rssiTextView: TextView
    private lateinit var deviceMacAddressTextView: TextView
    private lateinit var stateTextView: TextView

    private var deviceAddress: String? = null
    private var deviceName: String? = null
    private var lastState: String? = null

    private lateinit var checkBoxDeviceFound: CheckBox

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)

        // Initialize views
        deviceNameTextView = findViewById(R.id.deviceNameTextView)
        rssiTextView = findViewById(R.id.rssiTextView)
        deviceMacAddressTextView = findViewById(R.id.deviceMacAddressTextView)
        stateTextView = findViewById(R.id.stateTextView)
        checkBoxDeviceFound = findViewById(R.id.checkBoxDeviceFound) // Initialize CheckBox

        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // Get the device details passed from DeviceListActivity
        deviceName = intent.getStringExtra("DEVICE_NAME")
        deviceAddress = intent.getStringExtra("DEVICE_ADDRESS")

        // Set the device name and MAC address
        deviceNameTextView.text = deviceName ?: "Unknown Device"
        deviceMacAddressTextView.text = "MAC Address: $deviceAddress"

        // Initialize BLE adapter
        bleAdapter = BluetoothAdapterWrapper(this)

        // Check and enable Bluetooth
        bleAdapter.checkAndEnableBluetooth()

        // Start BLE scan to update RSSI
        startBLEScan()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun startBLEScan() {
        if (!hasPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                bleAdapter.getRequiredPermissions(),
                REQUEST_CODE_PERMISSIONS
            )
            return
        }

        val bluetoothLeScanner = bleAdapter.bluetoothAdapter?.bluetoothLeScanner
        if (bluetoothLeScanner == null) {
            showToast("BLE Scanner not available.")
            return
        }

        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .setLegacy(false)
            .setPhy(ScanSettings.PHY_LE_ALL_SUPPORTED)
            .build()

        val scanFilter = ScanFilter.Builder().build()

        if (!isScanning) {
            isScanning = true
            showToast("Starting BLE Scan...")
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            bluetoothLeScanner.startScan(listOf(scanFilter), scanSettings, bleScanCallback)
        }
    }

    private fun stopBLEScan() {
        if (isScanning) {
            val bluetoothLeScanner = bleAdapter.bluetoothAdapter?.bluetoothLeScanner
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            bluetoothLeScanner?.stopScan(bleScanCallback)
            isScanning = false
            showToast("Scan stopped.")
        }
    }

    private fun showDeviceFoundPopup() {
        val inflater = layoutInflater
        val popupView = inflater.inflate(R.layout.popup_device_found, null)

        // Find the ImageView inside the popup
        val deviceImageView: ImageView = popupView.findViewById(R.id.deviceFoundImage)

        // Set the appropriate image based on the device name
        when (deviceName) {
            "Iron_Man" -> deviceImageView.setImageResource(R.drawable.ironman)
            "Hulk" -> deviceImageView.setImageResource(R.drawable.hulk3)
            else -> deviceImageView.setImageResource(R.drawable.device_found_image) // Fallback image
        }

        val popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0)

        // Stop BLE scanning and prepare result data
        stopBLEScan()

        val resultIntent = Intent()
        resultIntent.putExtra("FOUND_DEVICE_NAME", deviceName)
        resultIntent.putExtra("FOUND_DEVICE_ADDRESS", deviceAddress)
        setResult(RESULT_OK, resultIntent)

        // Automatically dismiss the popup after 3 seconds and finish the activity
        popupView.postDelayed({
            popupWindow.dismiss()
            finish()
        }, 5000)
    }


    private val bleScanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val deviceAddress = result.device.address
            val rssi = result.rssi

            if (deviceAddress == this@GameplayActivity.deviceAddress) {
                rssiTextView.text = "RSSI: $rssi dBm"

                val currentState = when {
                    rssi in -75..-41 -> "Near"
                    rssi in -40..0 -> {
                        if (lastState != "Found") {
                            showDeviceFoundPopup()
                            checkBoxDeviceFound.isChecked = true // Mark CheckBox as checked
                        }
                        "Found"
                    }
                    rssi < -75 -> {
                        checkBoxDeviceFound.isChecked = false // Mark CheckBox as unchecked
                        "Far"
                    }
                    else -> null
                }

                if (currentState != null && currentState != lastState) {
                    stateTextView.text = "Device is $currentState"
                    lastState = currentState
                }
            }
        }

        override fun onScanFailed(errorCode: Int) {
            showToast("Scan failed with error code: $errorCode")
        }
    }

    private fun hasPermissions(): Boolean {
        val permissions = bleAdapter.getRequiredPermissions()
        return permissions.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS &&
            grantResults.isNotEmpty() &&
            grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        ) {
            startBLEScan()
        } else {
            showToast("Permission denied. Cannot scan BLE devices.")
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1
    }
}
