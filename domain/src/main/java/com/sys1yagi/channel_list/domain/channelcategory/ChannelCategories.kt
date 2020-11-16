package com.sys1yagi.channel_list.domain.channelcategory

import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel

data class ChannelCategories(
    val channel: SubscriptionChannel,
    val categories: List<AssignedCategory>
)
