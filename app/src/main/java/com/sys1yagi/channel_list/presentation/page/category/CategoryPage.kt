package com.sys1yagi.channel_list.presentation.page.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator
import com.sys1yagi.channel_list.presentation.typography

@Composable
fun CategoryPage() {
    val viewModel: CategoryPageViewModel = getViewModel()
    val viewState = viewModel.state.collectAsState()

    ChannelListDisplay(viewState.value)
}

@Composable
fun ChannelListDisplay(viewState: CategoryPageViewState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (viewState.initializing) {
            CenterCircularProgressIndicator()
        } else {
            CategoryList(viewState.categories)
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                icon = { Icon(Icons.Filled.Add) }
            )
        }
    ) {
        Surface(Modifier.fillMaxSize()) {
            if (categories.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("カテゴリはまだありません", style = typography.subtitle1)
                }
            } else {

            }
        }
    }
}
