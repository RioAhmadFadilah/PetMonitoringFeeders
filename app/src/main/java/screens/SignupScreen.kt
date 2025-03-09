package com.flouw.petmonitoringfeeders.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SignupScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            // Menggunakan Firebase Authentication untuk membuat pengguna baru
            auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                navController.navigate("main") // Navigate to main screen
            } else {
                errorMessage = task.exception?.message ?: "Signup failed"
            }
        }
        }) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}