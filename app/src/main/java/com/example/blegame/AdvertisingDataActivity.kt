package com.example.blegame

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class AdvertisingDataActivity : AppCompatActivity() {

    private lateinit var bleAdapter: BluetoothAdapterWrapper
    private lateinit var deviceInfo: TextView
    private lateinit var advertisingDataDetails: TextView
    private lateinit var temperatureMeterView: TemperatureViewMeter

    private var scanCallback: ScanCallback? = null

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1
        private const val TAG = "AdvertisingDataActivity"
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertising_data)

        bleAdapter = BluetoothAdapterWrapper(this)
        deviceInfo = findViewById(R.id.deviceInfo)
        advertisingDataDetails = findViewById(R.id.advertisingDataDetails)
        temperatureMeterView = findViewById(R.id.temperatureMeter)

        // Get extras from the Intent
        val selectedDeviceName = intent.getStringExtra("DEVICE_NAME")
        val selectedDeviceAddress = intent.getStringExtra("DEVICE_ADDRESS")
        val deviceType = intent.getStringExtra("DEVICE_TYPE")
        val backButton: ImageButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish() // Go back to the previous activity
        }

        // Update UI with selected device information
        if (selectedDeviceName != null && selectedDeviceAddress != null) {
            deviceInfo.text = "Device: $selectedDeviceName ($selectedDeviceAddress)"
        } else {
            showToast("Device details not found. Returning to previous screen.")
            finish()
            return
        }

        if (checkPermissions()) {
            startRealTimeScan(selectedDeviceAddress, selectedDeviceName, deviceType)
        } else {
            requestPermissions()
        }
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.BLUETOOTH_SCAN
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            REQUEST_CODE_PERMISSIONS
        )
    }

    private fun startRealTimeScan(selectedDeviceAddress: String, selectedDeviceName: String, deviceType: String?) {
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

        scanCallback = object : ScanCallback() {
            @SuppressLint("MissingPermission", "SuspiciousIndentation")
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                val deviceName = result.device.name
                val deviceAddress = result.device.address

                if (deviceName == selectedDeviceName && deviceAddress == selectedDeviceAddress) {
                    Log.d(TAG, "Matched Device: Name=$deviceName, Address=$deviceAddress")

                    val parsedData = parseAdvertisingData(result, deviceType)

                    Handler(Looper.getMainLooper()).post {
                        advertisingDataDetails.text = parsedData
                    }
                } else {
                    Log.d(TAG, "Skipped Device: Name=$deviceName, Address=$deviceAddress does not match criteria.")
                }
            }

            override fun onScanFailed(errorCode: Int) {
                Log.e(TAG, "Scan failed with error code: $errorCode")
                showToast("Scan failed with error code: $errorCode")
            }
        }

        if (checkPermissions()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            bluetoothLeScanner.startScan(null, scanSettings, scanCallback)
            Log.d(TAG, "Started scanning for BLE devices.")
        } else {
            showToast("Permissions not granted.")
        }
    }

    private fun parseAdvertisingData(result: ScanResult, deviceType: String?): String {
        val gapDetails = StringBuilder()
        val scanRecord = result.scanRecord

        // Manufacturer Data
        scanRecord?.manufacturerSpecificData?.let { manufacturerData ->
            for (i in 0 until manufacturerData.size()) {
                val data = manufacturerData.valueAt(i) // Data bytes

                val parsedData = when (deviceType) {
                    "SHT40" -> {
                        if (data.size >= 5) {
                            val deviceId = data[0].toUByte().toString()
                            val temperatureBeforeDecimal = data[1].toUByte().toString()
                            val temperatureAfterDecimal = data[2].toUByte().toString()
                            val humidityBeforeDecimal = data[3].toUByte().toString()
                            val humidityAfterDecimal = data[4].toUByte().toString()

                            val temperature = "$temperatureBeforeDecimal.$temperatureAfterDecimal".toFloat()

                            // Update Temperature Meter
                            Handler(Looper.getMainLooper()).post {
                                temperatureMeterView.setTemperature(temperature)
                            }

                            "Device ID: $deviceId\nTemperature: $temperatureBeforeDecimal.$temperatureAfterDecimal°C\n" +
                                    "Humidity: $humidityBeforeDecimal.$humidityAfterDecimal%"
                        } else {
                            "Manufacturer Data is too short for SHT40"
                        }
                    }
                    "LIS2DH" -> {
                        if (data.size >= 7) {
                            val deviceId = data[0].toUByte().toString()
                            val xBeforeDecimal = data[1].toUByte().toString()
                            val xAfterDecimal = data[2].toUByte().toString()
                            val yBeforeDecimal = data[3].toUByte().toString()
                            val yAfterDecimal = data[4].toUByte().toString()
                            val zBeforeDecimal = data[5].toUByte().toString()
                            val zAfterDecimal = data[6].toUByte().toString()

                            "Device ID: $deviceId\nX: $xBeforeDecimal.$xAfterDecimal\n" +
                                    "Y: $yBeforeDecimal.$yAfterDecimal\nZ: $zBeforeDecimal.$zAfterDecimal"
                        } else {
                            "Manufacturer Data is too short for LIS2DH"
                        }
                    }
                    else -> "Unknown Device Type"
                }

                gapDetails.append(parsedData).append("\n\n")
            }
        }

        // Service UUIDs
        scanRecord?.serviceUuids?.forEach {
            gapDetails.append("Service UUID: $it\n")
        }

        // Other Advertising Data
        scanRecord?.serviceData?.forEach { (uuid, data) ->
            gapDetails.append("Service Data UUID: $uuid -> ${data.joinToString(", ") { it.toString() }}\n")
        }

        return gapDetails.toString()
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        val bluetoothLeScanner = bleAdapter.bluetoothAdapter?.bluetoothLeScanner
        scanCallback?.let {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_SCAN
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            bluetoothLeScanner?.stopScan(it)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                val selectedDeviceName = intent.getStringExtra("DEVICE_NAME") ?: return
                val selectedDeviceAddress = intent.getStringExtra("DEVICE_ADDRESS") ?: return
                val deviceType = intent.getStringExtra("DEVICE_TYPE")
                startRealTimeScan(selectedDeviceAddress, selectedDeviceName, deviceType)
            } else {
                showToast("Permissions denied. Cannot scan for BLE devices.")
            }
        }
    }
}
