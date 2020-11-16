package com.sys1yagi.channel_list.domain.channelcategory

import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel

data class AssignedCategory(
    val channel: SubscriptionChannel,
    val category: Category,
    val assigned: Boolean
)
