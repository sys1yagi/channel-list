package com.sys1yagi.channel_list.presentation.page.addcategory

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sys1yagi.channel_list.R
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.category.Category

@Composable
fun CategoryEditPage(onClose: () -> Unit) {
    val viewModel: AddCategoryPageViewModel = getViewModel()
    val viewState = viewModel.state.collectAsState()

    if (viewState.value.closed) {
        onClose()
    } else {
        CategoryEditDisplay(viewState.value) {
            viewModel.addCategory(it)
        }
    }
}

@Composable
fun CategoryEditDisplay(
    viewState: AddCategoryPageViewState,
    onClickAddCategory: (category: Category) -> Unit
) {
    val (name, setName) = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                val title = stringResource(R.string.add_category)
                Text(title)
            })
        }
    ) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = {
                        if (!viewState.saved) {
                            setName(it)
                        }
                    },
                    placeholder = {
                        val placeHolder = stringResource(R.string.category_name)
                        Text(placeHolder)
                    })
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onClickAddCategory(
                            Category(null, name)
                        )
                    },
                    enabled = !viewState.saved
                ) {
                    Text("追加する")
                }
            }
        }
    }
}
