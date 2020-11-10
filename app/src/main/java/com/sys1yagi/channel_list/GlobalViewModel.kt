package com.sys1yagi.channel_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.domain.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GlobalViewModel(
    val authenticationRepository: AuthenticationRepository
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