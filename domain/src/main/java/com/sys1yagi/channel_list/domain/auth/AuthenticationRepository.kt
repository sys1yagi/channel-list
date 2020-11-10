package com.sys1yagi.channel_list.domain.auth

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun signIn()
    suspend fun signOut()
    fun loginState(): Flow<User?>
}
