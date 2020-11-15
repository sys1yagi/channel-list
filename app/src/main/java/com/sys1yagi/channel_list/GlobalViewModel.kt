package com.sys1yagi.channel_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.domain.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object AddCategory : Screen("add-category")
}

class GlobalViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    val loginState: Flow<User?>
        get() = authenticationRepository.loginState()

    init {
        // TODO
    }

    fun signIn() {
        viewModelScope.launch {
            authenticationRepository.signIn()
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authenticationRepository.signOut()
        }
    }
}

