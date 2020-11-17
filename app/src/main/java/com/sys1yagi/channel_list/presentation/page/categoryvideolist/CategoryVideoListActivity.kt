package com.sys1yagi.channel_list.presentation.page.categoryvideolist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.presentation.ChannellistTheme

// TODO 本当はnavigation内でやりたい
class CategoryVideoListActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, category: Category): Intent {
            return Intent(
                context, CategoryVideoListActivity::class.java
            ).apply {
                putExtra("categoryId", category.id!!.value)
                putExtra("categoryName", category.name)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId: Id<Category> = Id(intent.getLongExtra("categoryId", 0))
        val categoryName = intent.getStringExtra("categoryName") ?: ""
        setContent {
            ChannellistTheme {
                CategoryVideoListPage(categoryId, categoryName)
            }
        }
    }
}
