package com.sys1yagi.channel_list

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sys1yagi.channel_list.presentation.page.home.HomePage
import com.sys1yagi.channel_list.presentation.page.login.LoginPage

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
}

@Composable
fun App(startDestination: Screen, globalViewModel: GlobalViewModel) {
    val navController: NavHostController = rememberNavController()
    NavHost(navController, startDestination = startDestination.route) {
        composable(Screen.Home.route) {
            HomePage(globalViewModel)
        }
        composable(Screen.Login.route) {
            LoginPage(globalViewModel)
        }
    }
}