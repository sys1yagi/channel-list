package com.sys1yagi.channel_list.presentation.page.home

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sys1yagi.channel_list.GlobalViewModel
import com.sys1yagi.channel_list.R

@Composable
fun HomePage(globalViewModel: GlobalViewModel) {
    HomeDisplay {
        globalViewModel.signOut()
    }
}

@Composable
private fun HomeDisplay(onClickLogin: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                val title = stringResource(R.string.app_name)
                Text(title)
            })
        }
    ) {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Home")
                Button(onClick = onClickLogin) {
                    Text("SignOut")
                }
            }
        }
    }
}