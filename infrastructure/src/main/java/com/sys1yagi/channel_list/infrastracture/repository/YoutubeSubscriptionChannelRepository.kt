package com.sys1yagi.channel_list.infrastracture.repository

import com.google.api.services.youtube.YouTube
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelRepository
import com.sys1yagi.channel_list.infrastracture.database.SubscriptionChannelDao
import com.sys1yagi.channel_list.infrastracture.database.SubscriptionChannelEntity
import com.sys1yagi.channel_list.infrastracture.preference.SubscriptionChannelPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
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
        // TODO page tokenでページングする
        val response = service.subscriptions()
            .list(listOf("snippet,contentDetails"))
            .setMine(true)
            .setMaxResults(50)
            .execute()
        SubscriptionChannelPref.lastSyncDate = System.currentTimeMillis()
        val entities = response.items.map { item ->
            SubscriptionChannelEntity(
                item.snippet.resourceId.channelId,
                item.snippet.title,
                item.snippet.description,
                item.snippet.thumbnails.default.url,
                item.snippet.thumbnails.medium.url,
                item.snippet.thumbnails.high.url,
            )
        }
        // TODO daoでtransactionで処理する
        val all = subscriptionChannelDao.all()
        val diff = all - entities
        if(diff.isNotEmpty()) {
            // TODO channel categoryも削除する
            subscriptionChannelDao.delete(*(diff).toTypedArray())
        }
        subscriptionChannelDao.insert(
            *entities.toTypedArray()
        )
    }

    override fun subscriptionChannels(): Flow<List<SubscriptionChannel>> {
        return subscriptionChannelDao.subscribe().map {
            it.map(SubscriptionChannelEntity::toModel)
        }
    }

    override suspend fun findByChannelId(channelId: String): SubscriptionChannel? {
        return subscriptionChannelDao.findByChannelId(channelId)?.let(SubscriptionChannelEntity::toModel)
    }
}
