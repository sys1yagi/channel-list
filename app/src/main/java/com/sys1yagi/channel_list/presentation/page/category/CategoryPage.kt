package com.sys1yagi.channel_list.presentation.page.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator
import com.sys1yagi.channel_list.presentation.typography

@Composable
fun CategoryPage(onClickAddCategory: () -> Unit) {
    val viewModel: CategoryPageViewModel = getViewModel()
    val viewState = viewModel.state.collectAsState()

    ChannelListDisplay(viewState.value, onClickAddCategory)
}

@Composable
fun ChannelListDisplay(viewState: CategoryPageViewState, onClickAddCategory: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (viewState.initializing) {
            CenterCircularProgressIndicator()
        } else {
            CategoryList(viewState.categories, onClickAddCategory)
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>, onClickAddCategory: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickAddCategory,
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
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = 16.dp,
                        bottom = 8.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                ) {
                    items(categories) { category ->
                        CategoryCard(category)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    CategoryList(
        listOf(
            Category(null, "マラソン"),
            Category(null, "ゲーム"),
        )
    ) {

    }
}
