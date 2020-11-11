package com.sys1yagi.channel_list.di

import androidx.activity.ComponentActivity
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.YouTubeScopes
import com.sys1yagi.channel_list.GlobalViewModel
import com.sys1yagi.channel_list.MainActivity
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.infrastracture.googleapi.GoogleAuthenticationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val singleton by lazy {
    module {
        single {
            GoogleAccountCredential.usingOAuth2(
                androidContext(),
                listOf(
                    YouTubeScopes.YOUTUBE_READONLY
                )
            ).apply {
                backOff = ExponentialBackOff()
            }
        }
        single<YouTube> {
            YouTube.Builder(
                NetHttpTransport(),
                JacksonFactory(),
                get(),
            ).setApplicationName("com.sys1yagi.channel_list")
                .build()
        }
    }
}

val mainActivity by lazy {
    module {

        scope(named<MainActivity>()) {
            scoped<AuthenticationRepository> { (activity: ComponentActivity) ->
                GoogleAuthenticationRepository(
                    activity,
                    get(),
                    get()
                )
            }
            viewModel { (activity: ComponentActivity) ->
                GlobalViewModel(
                    get { parametersOf(activity) }
                )
            }
            scoped {
                GoogleAccountCredential.usingOAuth2(
                    androidContext(),
                    listOf(
                        YouTubeScopes.YOUTUBE_READONLY
                    )
                ).apply {
                    backOff = ExponentialBackOff()
                }
            }
            scoped<YouTube> {
                YouTube.Builder(
                    NetHttpTransport(),
                    JacksonFactory(),
                    get<GoogleAccountCredential>(),
                ).setApplicationName("com.sys1yagi.channel_list")
                    .build()
            }
        }
    }
}
