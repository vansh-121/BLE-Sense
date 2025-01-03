package com.example.blegame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BLEDeviceAdapter(
    private val devices: List<BLEDevice>,
    private val onClick: (BLEDevice) -> Unit
) : RecyclerView.Adapter<BLEDeviceAdapter.BLEDeviceViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_DEVICE = 1

    // Character Image Resource Mapping
    private val characterImages = mapOf(
        "Scarlet Witch" to R.drawable.scarlet_witch,
        "Black Widow" to R.drawable.black_widow,
        "Captain Marvel" to R.drawable.captain_marvel,
        "Wasp" to R.drawable.wasp,
        "Hela" to R.drawable.hela,
        "Hulk" to R.drawable.hulk3,
        "Thor" to R.drawable.thor,
        "Iron_Man" to R.drawable.iron_man1,
        "Spider Man" to R.drawable.spider_man,
        "Captain America" to R.drawable.captain_america
    )

    inner class BLEDeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.deviceNameTextView)
        val addressTextView: TextView = itemView.findViewById(R.id.deviceMacAddressTextView)
        val rssiTextView: TextView = itemView.findViewById(R.id.deviceRssi)
        val characterImageView: ImageView = itemView.findViewById(R.id.characterImageView)

        fun bind(device: BLEDevice) {
            nameTextView.text = device.name ?: "Unknown Device"
            addressTextView.text = device.address
            rssiTextView.text = "RSSI: ${device.rssi} dBm"

            // Set the character image based on the device name, or use a default image if not found
            val imageResId = characterImages[device.name] ?: R.drawable.notfound
            characterImageView.setImageResource(imageResId)

            // Set click listener for the item
            itemView.setOnClickListener { onClick(device) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLEDeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_device_card, parent, false)
        return BLEDeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BLEDeviceViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            // Partial update: Only update RSSI value
            val updatedRSSI = payloads[0] as? String
            holder.rssiTextView.text = "RSSI: $updatedRSSI dBm"
        } else {
            // Full update: Bind the entire device data
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: BLEDeviceViewHolder, position: Int) {
        holder.bind(devices[position])
    }

    override fun getItemCount(): Int = devices.size
}
