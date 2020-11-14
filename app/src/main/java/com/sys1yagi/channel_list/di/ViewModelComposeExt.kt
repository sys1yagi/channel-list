package com.sys1yagi.channel_list.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ViewModelStoreOwnerAmbient
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ViewModelOwner.Companion.from
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
inline fun <reified T : ViewModel> getViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    val owner = ViewModelStoreOwnerAmbient.current.viewModelStore
    return remember {
        GlobalContext.get().getViewModel(qualifier, owner = { from(owner) }, parameters = parameters)
    }
}
