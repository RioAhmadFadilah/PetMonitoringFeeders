package com.flouw.petmonitoringfeeders.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Reset Kata Sandi", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    if (email.isEmpty()) {
                        message = "Silakan masukkan email Anda."
                    } else {
                        isLoading = true
                        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener { task ->
                                isLoading = false
                                message = if (task.isSuccessful) {
                                    "Link reset password telah dikirim ke email Anda."
                                } else {
                                    task.exception?.message ?: "Gagal mengirim reset password."
                                }
                            }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kirim Reset Password")
            }
        }

        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(message, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text("Kembali ke Login")
        }
    }
}
