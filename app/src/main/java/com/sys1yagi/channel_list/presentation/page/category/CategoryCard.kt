package com.sys1yagi.channel_list.presentation.page.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.category.CategoryWithAssignedChannelCount
import com.sys1yagi.channel_list.presentation.typography

@Composable
fun CategoryCard(categoryWithCount: CategoryWithAssignedChannelCount, onClick: (Category) -> Unit) {
    val (category, count) = categoryWithCount
    Card(
        modifier =
        Modifier.padding(8.dp)
            .then(Modifier.fillMaxWidth())
            .then(Modifier.clickable(onClick = { onClick(category) })),
        elevation = 2.dp
    ) {
        Surface(Modifier.padding(16.dp)) {
            Column {
                Text(category.name, style = typography.h6)
                Spacer(modifier = Modifier.height(4.dp))
                Text("登録チャンネル数: ${count}件", style = typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCategoryCard() {
    CategoryCard(
        CategoryWithAssignedChannelCount(
            Category(null, "マラソン"),
            10
        )
    ) {}
}
