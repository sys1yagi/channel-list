package com.sys1yagi.channel_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.ui.ChannellistTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChannellistTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val (text, setText) = remember { mutableStateOf(name) }
    Button(onClick = {
        setText("Compose")
    }) {
        Text(text = "Hello $text!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChannellistTheme {
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.padding(16.dp)
        ) {
            Greeting("Android")
        }
    }
}