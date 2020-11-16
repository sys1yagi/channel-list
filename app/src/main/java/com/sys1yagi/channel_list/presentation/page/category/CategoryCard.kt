package com.sys1yagi.channel_list.presentation.page.category

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.presentation.typography

@Composable
fun CategoryCard(category: Category) {
    Card(modifier = Modifier.padding(8.dp).then(Modifier.fillMaxWidth())) {
        Surface(Modifier.padding(16.dp)) {
            Text(category.name, style = typography.h6)
        }
    }
}
