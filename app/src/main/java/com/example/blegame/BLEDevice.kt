package com.example.blegame

data class BLEDevice(
    var name: String?,
    var address: String?,
    var rssi: String = 0.toString(),
    var lastSeen: Long = System.currentTimeMillis()
)