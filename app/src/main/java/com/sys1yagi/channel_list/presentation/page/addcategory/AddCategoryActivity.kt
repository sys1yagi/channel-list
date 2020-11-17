package com.sys1yagi.channel_list.presentation.page.addcategory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.sys1yagi.channel_list.presentation.ChannellistTheme

// TODO 本当はnavigation内でやりたい
class AddCategoryActivity : AppCompatActivity() {
    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, AddCategoryActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChannellistTheme {
                CategoryEditPage {
                    finish()
                }
            }
        }
    }
}
