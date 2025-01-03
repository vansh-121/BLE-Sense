package com.example.blegame

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BLEAppActivity : AppCompatActivity() {

    private lateinit var bleAdapter: BluetoothAdapterWrapper
    private lateinit var deviceAdapter: BLEDeviceAdapter
    private val deviceList = mutableListOf<BLEDevice>()
    private val filteredDeviceList = mutableListOf<BLEDevice>() // Filtered list
    private val deviceRSSI = mutableMapOf<String, Int>() // To store the device and its RSSI value
    private val deviceAddresses = mutableSetOf<String>() // To track the MAC addresses of the devices
    private var selectedDeviceType: String = "SHT40" // Default BLE Device Type
    private var isScanning = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ble_device_list)

        bleAdapter = BluetoothAdapterWrapper(this)

        val deviceRecyclerView: RecyclerView = findViewById(R.id.deviceRecyclerView)
        deviceAdapter = BLEDeviceAdapter(filteredDeviceList) { selectedDevice ->
            handleDeviceClick(selectedDevice)
        }
        deviceRecyclerView.layoutManager = LinearLayoutManager(this)
        deviceRecyclerView.adapter = deviceAdapter

        val backButton: ImageButton = findViewById(R.id.backButton)
        val autoRenewButton: ImageButton = findViewById(R.id.autorenew)
        val filterSpinner: Spinner = findViewById(R.id.filterSpinner)
        val deviceTypeSpinner: Spinner = findViewById(R.id.deviceTypeSpinner) // Add spinner for device types

        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }

        autoRenewButton.setOnClickListener {
            refreshBLEScan() // Trigger BLE rescan when the button is clicked
        }

        setupFilterSpinner(filterSpinner)
        setupDeviceTypeSpinner(deviceTypeSpinner)
        bleAdapter.checkAndEnableBluetooth()
        startBLEScan()
    }

    private fun setupFilterSpinner(filterSpinner: Spinner) {
        val filterOptions = listOf("All Devices", "Strong Signal", "Weak Signal")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, filterOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        filterSpinner.adapter = adapter

        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                when (position) {
                    0 -> filterDevicesBy { true } // All devices
                    1 -> filterDevicesBy { it.rssi.toInt() > -70 } // Strong Signal (RSSI > -70)
                    2 -> filterDevicesBy { it.rssi.toInt() <= -70 } // Weak Signal (RSSI <= -70)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No action needed
            }
        }
    }

    private fun setupDeviceTypeSpinner(deviceTypeSpinner: Spinner) {
        val deviceTypes = listOf("SHT40", "LIS2DH")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, deviceTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        deviceTypeSpinner.adapter = adapter

        deviceTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedDeviceType = deviceTypes[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Default to the first option if nothing is selected
                selectedDeviceType = "SHT40"
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterDevicesBy(predicate: (BLEDevice) -> Boolean) {
        filteredDeviceList.clear()
        filteredDeviceList.addAll(deviceList.filter(predicate))
        deviceAdapter.notifyDataSetChanged()
    }

    private fun handleDeviceClick(device: BLEDevice) {
        val intent = Intent(this, AdvertisingDataActivity::class.java)
        intent.putExtra("DEVICE_NAME", device.name)
        intent.putExtra("DEVICE_ADDRESS", device.address)
        intent.putExtra("DEVICE_TYPE", selectedDeviceType) // Pass the selected device type
        startActivity(intent)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshBLEScan() {
        deviceList.clear()
        filteredDeviceList.clear()
        deviceRSSI.clear()
        deviceAddresses.clear()
        deviceAdapter.notifyDataSetChanged()
        startBLEScan()
    }

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

        val scanFilters = listOf<ScanFilter>()

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

            bluetoothLeScanner.startScan(scanFilters, scanSettings, bleScanCallback)
        }
    }

    private val bleScanCallback = object : ScanCallback() {
        @SuppressLint("MissingPermission")
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            val deviceName = result.device.name
            val deviceAddress = result.device.address
            val rssi = result.rssi

            if (deviceName.isNullOrEmpty()) return

            val existingIndex = deviceList.indexOfFirst { it.address == deviceAddress }
            if (existingIndex != -1) {
                deviceList[existingIndex].rssi = rssi.toString()
                deviceAdapter.notifyItemChanged(existingIndex, rssi.toString())
            } else {
                val newDevice = BLEDevice(deviceName, deviceAddress, rssi.toString())
                deviceList.add(newDevice)
                filteredDeviceList.add(newDevice)
                deviceAddresses.add(deviceAddress)
                deviceAdapter.notifyItemInserted(filteredDeviceList.size - 1)
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
        private const val REQUEST_CODE_PERMISSIONS = 1002
    }
}
