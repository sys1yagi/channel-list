package com.sys1yagi.channel_list.presentation.page.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.GlobalViewModelAmbient

@Composable
fun Setting() {
    val globalViewModel = GlobalViewModelAmbient.current
    SettingDisplay {
        globalViewModel.signOut()
    }
}

@Composable
private fun SettingDisplay(onClickSignOut: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = onClickSignOut) {
                Text("SignOut")
            }
        }
    }
}

@Preview
@Composable
fun LoginDisplayPreview() {
    SettingDisplay {
        // no op
    }
}
