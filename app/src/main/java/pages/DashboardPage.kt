package com.flouw.petmonitoringfeeders.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flouw.petmonitoringfeeders.viewmodel.DashboardViewModel

@Composable
fun DashboardPage(viewModel: DashboardViewModel = viewModel()) {
    val foodPercentage by viewModel.foodPercentage.collectAsState()  // Observe ViewModel

    Scaffold(
        containerColor = Color.Black
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "General",
                fontSize = 28.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Card - Persentase Sisa Makanan
            FoodPercentageCard(foodPercentage)
        }
    }
}

@Composable
fun FoodPercentageCard(percentage: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1C1C)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sisa Makanan", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Circular Progress Indicator
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    progress = percentage / 100f,
                    color = Color(0xFFFF9800),
                    strokeWidth = 8.dp,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "$percentage%",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}
