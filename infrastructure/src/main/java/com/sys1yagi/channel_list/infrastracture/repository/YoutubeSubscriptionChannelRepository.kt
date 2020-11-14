package com.sys1yagi.channel_list.infrastracture.repository

import android.util.Log
import com.google.api.services.youtube.YouTube
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelRepository
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.infrastracture.database.SubscriptionChannelDao
import com.sys1yagi.channel_list.infrastracture.database.SubscriptionChannelEntity
import com.sys1yagi.channel_list.infrastracture.preference.SubscriptionChannelPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class YoutubeSubscriptionChannelRepository(
    private val service: YouTube,
    private val subscriptionChannelDao: SubscriptionChannelDao
) : SubscriptionChannelRepository {
    override suspend fun lastSyncDate(): LocalDateTime? {
        val lastSyncDate = SubscriptionChannelPref.lastSyncDate
        if (lastSyncDate == -1L) {
            return null
        } else {
            return LocalDateTime.ofInstant(
                Instant.ofEpochMilli(lastSyncDate),
                ZoneId.systemDefault()
            )
        }
    }

    override suspend fun syncSubscriptionChannels() = withContext<Unit>(Dispatchers.IO) {
        val response = service.subscriptions()
            .list(listOf("snippet,contentDetails"))
            .setMine(true)
            .setMaxResults(50)
            .execute()
        SubscriptionChannelPref.lastSyncDate = System.currentTimeMillis()
        subscriptionChannelDao.insert(
            *response.items.map { item ->
                SubscriptionChannelEntity(
                    0L,
                    item.snippet.title,
                    item.snippet.description,
                    item.snippet.resourceId.channelId,
                    item.snippet.thumbnails.default.url,
                    item.snippet.thumbnails.medium.url,
                    item.snippet.thumbnails.high.url,
                )
            }.toTypedArray()
        )
    }

    override fun subscriptionChannels(): Flow<List<SubscriptionChannel>> {
        return subscriptionChannelDao.subscribe().map {
            it.map { item ->
                SubscriptionChannel(
                    item.title,
                    item.description,
                    item.channelId,
                    Thumbnails(
                        Thumbnail(item.thumbnailDefault),
                        Thumbnail(item.thumbnailMedium),
                        Thumbnail(item.thumbnailHigh)
                    )
                )
            }
        }
    }
}
