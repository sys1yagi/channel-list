package com.sys1yagi.channel_list.presentation.page.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.GlobalViewModel
import com.sys1yagi.channel_list.GlobalViewModelAmbient
import com.sys1yagi.channel_list.R

@Composable
fun LoginPage() {
    val viewModel = GlobalViewModelAmbient.current
    LoginDisplay {
        viewModel.signIn()
    }
}

@Composable
private fun LoginDisplay(onClickLogin: () -> Unit) {
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
                Button(onClick=onClickLogin) {
                    Text("SignIn")
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginDisplayPreview() {
    LoginDisplay {
        // no op
    }
}
