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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.category.CategoryWithAssignedChannelCount
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator
import com.sys1yagi.channel_list.presentation.typography

@Composable
fun CategoryPage(
    onClickAddCategory: () -> Unit,
    onClickCategory: (Category) -> Unit
) {
    val viewModel: CategoryPageViewModel = getViewModel()
    val viewState = viewModel.state.collectAsState()

    ChannelListDisplay(viewState.value, onClickAddCategory, onClickCategory)
}

@Composable
fun ChannelListDisplay(
    viewState: CategoryPageViewState,
    onClickAddCategory: () -> Unit,
    onClickCategory: (Category) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (viewState.initializing) {
            CenterCircularProgressIndicator()
        } else {
            CategoryList(viewState.categories, onClickAddCategory, onClickCategory)
        }
    }
}

@Composable
fun CategoryList(
    categories: List<CategoryWithAssignedChannelCount>,
    onClickAddCategory: () -> Unit,
    onClickCategory: (Category) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickAddCategory,
                content = { Icon(Icons.Filled.Add, contentDescription = null) }
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
                    items(categories.size) { i ->
                        CategoryCard(categories[i], onClickCategory)
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
            CategoryWithAssignedChannelCount(
                Category(null, "マラソン"),
                10
            ),
            CategoryWithAssignedChannelCount(
                Category(null, "ゲーム"),
                8
            )
        ),
        {},
        {}
    )
}
