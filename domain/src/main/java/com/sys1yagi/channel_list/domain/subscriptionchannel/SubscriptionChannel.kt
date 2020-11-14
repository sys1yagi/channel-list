package com.sys1yagi.channel_list.domain.subscriptionchannel

data class SubscriptionChannel(
    val title: String,
    val description: String,
    val channelId: String,
    val thumbnails: Thumbnails,
)
