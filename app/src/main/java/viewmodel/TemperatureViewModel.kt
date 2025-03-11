package com.flouw.petmonitoringfeeders.viewmodel  // ✅ Package sesuai struktur folder

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TemperatureViewModel : ViewModel() {
    private val _temperature = MutableStateFlow("Loading...")
    val temperature = _temperature.asStateFlow()

    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("temperature")

    init {
        fetchTemperature()
    }

    private fun fetchTemperature() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tempValue = snapshot.getValue(Double::class.java)
                _temperature.value = if (tempValue != null) {
                    "$tempValue °C"
                } else {
                    "N/A"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error fetching temperature", error.toException())
                _temperature.value = "Error"
            }
        })
    }
}
