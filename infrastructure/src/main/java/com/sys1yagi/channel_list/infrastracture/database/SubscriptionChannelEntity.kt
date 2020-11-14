package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails

@Entity(
    tableName = "subscription_channels",
    indices = [Index(
        value = ["channelId"],
        unique = true
    )],
)
data class SubscriptionChannelEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val description: String,
    val channelId: String,
    val thumbnailDefault: String,
    val thumbnailMedium: String,
    val thumbnailHigh: String
) {
    fun toModel() = SubscriptionChannel(
        title,
        description,
        channelId,
        Thumbnails(
            Thumbnail(thumbnailDefault),
            Thumbnail(thumbnailMedium),
            Thumbnail(thumbnailHigh)
        )
    )
}
