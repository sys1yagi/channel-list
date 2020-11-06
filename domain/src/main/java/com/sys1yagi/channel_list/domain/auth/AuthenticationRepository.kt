package com.sys1yagi.channel_list.domain.auth

interface AuthenticationRepository {
    suspend fun currentUser(): User?
    suspend fun signIn()
    suspend fun signOut()
}
