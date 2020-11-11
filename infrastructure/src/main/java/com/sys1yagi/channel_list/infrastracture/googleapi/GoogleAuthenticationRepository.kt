package com.sys1yagi.channel_list.infrastracture.googleapi

import android.accounts.AccountManager
import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.services.youtube.YouTube
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.domain.auth.User
import com.sys1yagi.channel_list.infrastracture.preference.AuthenticationSetting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GoogleAuthenticationRepository(
    activity: ComponentActivity,
    val credential: GoogleAccountCredential,
    val service: YouTube
) : AuthenticationRepository {
    private var user = MutableStateFlow(
        AuthenticationSetting.accountName?.let { accountName ->
            if (AuthenticationSetting.permitted) {
                User(accountName)
            } else {
                null
            }
        }
    )

    // 利用するGoogleアカウントを選択した結果
    private val accountChoice = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val accountName = it.data?.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
                ?: return@registerForActivityResult
            AuthenticationSetting.accountName = accountName
            GlobalScope.launch {
                startGetPermission(accountName)
            }
        }
    }

    // 選択したGoogleアカウントのYoutubeへのRead権限の許諾結果
    private val getPermission = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            AuthenticationSetting.permitted = true
            AuthenticationSetting.accountName?.let {
                user.value = User(it)
            }
        }
    }

    override fun loginState(): StateFlow<User?> {
        return user
    }

    override suspend fun signIn() {
        val accountName = AuthenticationSetting.accountName
        if (accountName == null) {
            accountChoice.launch(credential.newChooseAccountIntent())
        } else {
            startGetPermission(accountName)
        }
    }

    private suspend fun startGetPermission(accountName: String) = withContext(Dispatchers.IO) {
        credential.selectedAccountName = accountName
        try {
            service.subscriptions()
                .list(listOf("snippet,contentDetails"))
                .setMine(true)
                .setMaxResults(1)
                .execute()
            AuthenticationSetting.permitted = true
            user.value = User(accountName)
        } catch (e: UserRecoverableAuthIOException) {
            getPermission.launch(e.intent)
        }
    }

    override suspend fun signOut() {
        AuthenticationSetting.accountName = null
        AuthenticationSetting.permitted = false
        this.user.value = null
    }
}
