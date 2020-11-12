package com.sys1yagi.channel_list

import android.os.Bundle
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ambientOf
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.setContent
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

val GlobalViewModelAmbient = ambientOf<GlobalViewModel>()

class MainActivity : ScopeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModel<GlobalViewModel> { parametersOf(this) }
        viewModel // don't remove. you should initialize ViewModel while onCreate.

        setContent {
            Providers(GlobalViewModelAmbient provides viewModel) {
                ChannellistTheme {
                    App()
                }
            }
        }
    }
}
