package com.sys1yagi.channel_list.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable


@Composable
fun NavigationUpTopAppBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onBack) {
                Icon(Icons.Filled.ArrowBack)
            }
        },
        title = {
            Text(title)
        }
    )
}
