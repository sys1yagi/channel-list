package com.sys1yagi.channel_list.presentation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.R

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    primaryVariant = primaryLight,
    secondary = primaryDark
)

private val LightColorPalette = lightColors(
    primary = primary,
    primaryVariant = primaryLight,
    secondary = primary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ChannellistTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Preview
@Composable
fun PreviewLightTheme() {
    Preview(false)
}

@Preview
@Composable
fun PreviewDarkTheme() {
    Preview(true)
}

@Composable
fun Preview(darkTheme: Boolean) {
    ChannellistTheme(darkTheme) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    val title = stringResource(R.string.app_name)
                    Text(title)
                })
            },
            bottomBar = {
                BottomNavigation {
                    BottomNavigationItem(
                        icon = { Icon(imageVector = Icons.Filled.List, contentDescription = null) },
                        label = { Text("ホーム") },
                        selected = true,
                        onClick = {}
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Category,
                                contentDescription = null
                            )
                        },
                        label = { Text("カテゴリ") },
                        selected = false,
                        onClick = {}
                    )
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = null
                            )
                        },
                        label = { Text("設定") },
                        selected = false,
                        onClick = {}
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {},
                    content = { Icon(imageVector = Icons.Filled.Add, contentDescription = null) }
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .then(Modifier.padding(it))
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Hello", style = typography.h1)
                }
            }
        }
    }
}
