package com.sys1yagi.channel_list

import androidx.compose.animation.Crossfade
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
    Crossfade(current = loginState.value) {
        if(it == null) {
            LoginPage()
        }
        else{
            HomePage()
        }
    }
}
