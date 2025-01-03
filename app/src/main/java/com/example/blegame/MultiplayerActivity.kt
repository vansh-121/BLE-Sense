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
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MultiplayerActivity : AppCompatActivity() {

    private lateinit var bleAdapter: BluetoothAdapterWrapper
    private lateinit var deviceAdapter: BLEDeviceAdapter
    private val deviceList = mutableListOf<BLEDevice>()
    private val deviceRSSI = mutableMapOf<String, Int>()
    private val filteredDeviceList = mutableListOf<BLEDevice>() //
    private val deviceAddresses = mutableSetOf<String>()
    private var selectedDevice: String? = null
    private var isScanning = false

    private val targetDevices = setOf(
        "Scarlet Witch", "Black Widow", "Captain Marvel", "Wasp", "Hela",
        "Hulk", "Thor", "Iron_Man", "Spider Man", "Captain America"
    )

    private val targetMacAddresses = setOf(
        "EA:17:DF:DF:4B:82", // Replace with your sensor's MAC addresses
        "EB:17:DF:DF:4B:81"
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_device_list)

        bleAdapter = BluetoothAdapterWrapper(this)

        val deviceRecyclerView: RecyclerView = findViewById(R.id.deviceRecyclerView)
        deviceAdapter = BLEDeviceAdapter(deviceList) { selectedDevice ->
            handleDeviceClick(selectedDevice)
        }
        deviceRecyclerView.layoutManager = LinearLayoutManager(this)
        deviceRecyclerView.adapter = deviceAdapter

        val backButton: ImageButton = findViewById(R.id.backButton)
        val autoRenewButton: ImageButton = findViewById(R.id.autorenew)

        backButton.setOnClickListener {
            finish()
        }

        autoRenewButton.setOnClickListener {
            refreshBLEScan()
        }

        bleAdapter.checkAndEnableBluetooth()
        startBLEScan()

        selectedDevice = intent.getStringExtra("SELECTED_DEVICE")
    }

    private fun handleDeviceClick(device: BLEDevice) {
        selectedDevice = "${device.name} (${device.address})"

        val intent = Intent(this, GameplayActivity::class.java)
        intent.putExtra("DEVICE_NAME", device.name)
        intent.putExtra("DEVICE_ADDRESS", device.address)
        startActivityForResult(intent, GAMEPLAY_ACTIVITY_REQUEST_CODE)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun refreshBLEScan() {
        stopBLEScan()
        deviceList.clear()
        deviceRSSI.clear()
        deviceAddresses.clear()
        deviceAdapter.notifyDataSetChanged()
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

            bluetoothLeScanner.startScan(
                listOf(scanFilter),
                scanSettings,
                bleScanCallback
            )
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

    private val bleScanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val deviceName = result.device.name
            val deviceAddress = result.device.address
            val rssi = result.rssi

            // Exclude devices that are not in the target list
            if (deviceName.isNullOrEmpty() || deviceName !in targetDevices) {
                return
            }

            val existingIndex = deviceList.indexOfFirst { it.address == deviceAddress }

            val displayName = if (rssi >= -30) deviceName else null
            val displayAddress = if (rssi >= -30) deviceAddress else null
            val rssiDisplay = rssi.toString()

            if (existingIndex != -1) {
                // Update the device information
                deviceList[existingIndex].apply {
                    name = displayName
                    address = displayAddress
                    this.rssi = rssiDisplay
                }
                deviceAdapter.notifyItemChanged(existingIndex)
            } else {
                // Add the device to the list if it's not already there
                val newDevice = BLEDevice(displayName, displayAddress, rssiDisplay)
                deviceList.add(newDevice)
                deviceAddresses.add(deviceAddress)
                deviceAdapter.notifyItemInserted(deviceList.size - 1)
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
        private const val GAMEPLAY_ACTIVITY_REQUEST_CODE = 1001
        private const val REQUEST_CODE_PERMISSIONS = 1002
    }
}
