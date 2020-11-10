package com.sys1yagi.channel_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.domain.auth.User
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import com.sys1yagi.channel_list.presentation.util.viewModelProviderFactoryOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = object : AuthenticationRepository {
            var user = MutableStateFlow<User?>(null)
            override fun loginState(): StateFlow<User?> {
                return user
            }

            override suspend fun signIn() {
                this.user.value = User()
            }

            override suspend fun signOut() {
                this.user.value = null
            }
        }

        setContent {
            val viewModel = viewModel<GlobalViewModel>(
                factory = viewModelProviderFactoryOf {
                    GlobalViewModel(
                        repo
                    )
                }
            )
            val loginState = viewModel.loginState.collectAsState(null)
            val screen = loginState.value?.let {
                Screen.Home
            } ?: Screen.Login
            ChannellistTheme {
                App(startDestination = screen, globalViewModel = viewModel)
            }
        }
    }
}
