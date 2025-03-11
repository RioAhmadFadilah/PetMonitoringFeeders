package com.flouw.petmonitoringfeeders.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material.icons.filled.PlaylistPlay
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flouw.petmonitoringfeeders.R

@Composable
fun SettingsPage() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF8F9FA))
    ) {
        // 🔹 Header dengan Foto Profil
        Box(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF3F3D56)).padding(vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logo), // Ganti dengan gambar profil dari drawable
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(80.dp).background(Color.Gray, shape = CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Alex Flores", fontSize = 20.sp, color = Color.White)
                Text(text = "alexflores@gmail.com", fontSize = 14.sp, color = Color.White.copy(0.7f))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 List Menu
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            SettingItem("Notification", Icons.Default.Notifications, Color(0xFFD1C4E9))
            SettingItem("Calendar", Icons.Default.CalendarToday, Color(0xFFFFCCBC))
            SettingItem("Gallery", Icons.Default.PermMedia, Color(0xFFCE93D8))
            SettingItem("My Playlist", Icons.Default.PlaylistPlay, Color(0xFFA5D6A7))
            SettingItem("Share", Icons.Default.Share, Color(0xFFFFAB91))
            SettingItem("Logout", Icons.Default.ExitToApp, Color(0xFFB0BEC5))
        }
    }
}

// 🔹 Komponen untuk setiap item menu
@Composable
fun SettingItem(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, bgColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable { },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).background(bgColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = Color.White)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = title, fontSize = 16.sp, color = Color.Black)
        }
    }
}
