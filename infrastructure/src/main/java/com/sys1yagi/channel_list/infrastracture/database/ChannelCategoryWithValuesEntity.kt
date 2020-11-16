package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Embedded
import androidx.room.Relation

class ChannelCategoryWithValuesEntity {
    @Embedded
    lateinit var channelCategory: ChannelCategoryEntity

    @Relation(parentColumn = "channelId", entityColumn = "channelId")
    lateinit var channel: SubscriptionChannelEntity

    @Relation(parentColumn = "categoryId", entityColumn = "id")
    lateinit var category: CategoryEntity
}
