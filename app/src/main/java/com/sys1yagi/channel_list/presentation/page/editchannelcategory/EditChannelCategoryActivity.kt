package com.sys1yagi.channel_list.presentation.page.editchannelcategory

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import com.sys1yagi.channel_list.presentation.page.addcategory.AddCategoryActivity

// TODO 本当はnavigation内でやりたい
class EditChannelCategoryActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context, channel: SubscriptionChannel): Intent {
            return Intent(context, EditChannelCategoryActivity::class.java).apply {
                putExtra("channelId", channel.channelId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val channelId = intent.getStringExtra("channelId")!!

        setContent {
            ChannellistTheme {
                val context = ContextAmbient.current
                EditChannelCategoryPage(channelId) {
                    context.startActivity(AddCategoryActivity.createIntent(context))
                }
            }
        }
    }
}
