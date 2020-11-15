package com.sys1yagi.channel_list

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.sys1yagi.channel_list.presentation.page.home.HomePage
import com.sys1yagi.channel_list.presentation.page.login.LoginPage

@Composable
fun App() {
    val viewModel = GlobalViewModelAmbient.current
    val loginState = viewModel.loginState.collectAsState(null)
    Crossfade(current = loginState.value) {
        if (it == null) {
            LoginPage()
        } else {
            HomePage()
        }
    }
}
