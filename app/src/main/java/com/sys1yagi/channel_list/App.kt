package com.sys1yagi.channel_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.onCommit
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.sys1yagi.channel_list.presentation.page.home.HomePage
import com.sys1yagi.channel_list.presentation.page.login.LoginPage

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
}

@Composable
fun App() {
    val viewModel = GlobalViewModelAmbient.current
    val loginState = viewModel.loginState.collectAsState(null)
    val navController: NavHostController = rememberNavController()

    onCommit(loginState.value) {
        val navBackStackEntry = navController.currentBackStackEntry
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        if(loginState.value != null && currentRoute == Screen.Login.route) {
            navController.navigate(Screen.Home.route)
        }
        if(loginState.value == null && currentRoute != null && currentRoute != Screen.Login.route) {
            navController.navigate(Screen.Login.route)
        }
    }

    NavHost(navController,
        startDestination = Screen.Login.route,
    ) {
        composable(Screen.Home.route) {
            HomePage()
        }
        composable(Screen.Login.route) {
            LoginPage(viewModel)
        }
    }
}
