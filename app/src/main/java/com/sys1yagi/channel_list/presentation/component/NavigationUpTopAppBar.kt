package com.sys1yagi.channel_list.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.presentation.ChannellistTheme


@Composable
fun NavigationUpTopAppBar(title: String, onBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onBack, modifier = Modifier.semantics {
                this.text = AnnotatedString("back button")
            }) {
                Icon(Icons.Filled.ArrowBack)
            }
        },
        title = {
            Text(title)
        }
    )
}

@Preview
@Composable
fun PreviewNavigationUpTopAppBar() {
    ChannellistTheme {
        NavigationUpTopAppBar("preview"){}
    }
}
