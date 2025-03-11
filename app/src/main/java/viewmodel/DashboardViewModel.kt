package com.flouw.petmonitoringfeeders.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val _foodPercentage = MutableStateFlow(0)  // Default 0%
    val foodPercentage: StateFlow<Int> = _foodPercentage

    init {
        fetchFoodPercentage()
    }

    private fun fetchFoodPercentage() {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("sensor/food_percentage") // Path di Firebase

        ref.addValueEventListener(object : com.google.firebase.database.ValueEventListener {
            override fun onDataChange(snapshot: com.google.firebase.database.DataSnapshot) {
                val value = snapshot.getValue(Int::class.java) ?: 0
                _foodPercentage.value = value
            }

            override fun onCancelled(error: com.google.firebase.database.DatabaseError) {}
        })
    }
}
