package com.flouw.petmonitoringfeeders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flouw.petmonitoringfeeders.screens.LoginScreen
import com.flouw.petmonitoringfeeders.screens.MainScreen
import com.flouw.petmonitoringfeeders.screens.SignupScreen
import com.flouw.petmonitoringfeeders.screens.SplashScreen
import com.flouw.petmonitoringfeeders.screens.ForgotPasswordScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenApp()
        }
    }
}

@Composable
fun SplashScreenApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignupScreen(navController)
        }
        composable("forgot_password") {
            ForgotPasswordScreen(navController)
        }
        composable("main") {
            MainScreen(navController = navController) // Perbaikan: Menambahkan navController
        }
    }
}