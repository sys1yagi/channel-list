package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "channel_categories",
    primaryKeys = [
        "channelId", "categoryId"
    ],
    foreignKeys = [
        ForeignKey(
            entity = SubscriptionChannelEntity::class,
            parentColumns = ["channelId"],
            childColumns = ["channelId"],

        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
        )
    ],
    indices = [
        Index(
            value = ["channelId", "categoryId"],
            unique = true
        ),
        Index(
            value = ["channelId"],
            unique = false
        ),
        Index(
            value = ["categoryId"],
            unique = false
        )
    ],
)
data class ChannelCategoryEntity(
    val channelId: String,
    val categoryId: Long
)
