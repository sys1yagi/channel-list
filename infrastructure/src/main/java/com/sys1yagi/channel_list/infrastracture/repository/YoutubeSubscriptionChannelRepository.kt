package com.sys1yagi.channel_list.infrastracture.repository

import com.google.api.services.youtube.YouTube
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelRepository
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class YoutubeSubscriptionChannelRepository(
    val service: YouTube,
) : SubscriptionChannelRepository {
    private var lastSyncDate: LocalDateTime? = null
    override suspend fun lastSyncDate(): LocalDateTime? {
        return lastSyncDate
    }

    private var subscriptionChannels = MutableStateFlow<List<SubscriptionChannel>>(emptyList())

    override suspend fun syncSubscriptionChannels() = withContext<Unit>(Dispatchers.IO) {
        val response = service.subscriptions()
            .list(listOf("snippet,contentDetails"))
            .setMine(true)
            .setMaxResults(50)
            .execute()
        lastSyncDate = LocalDateTime.now()
        subscriptionChannels.value = response.items.map {item ->
            SubscriptionChannel(
                item.snippet.title,
                item.snippet.description,
                item.snippet.channelId,
                Thumbnails(
                    Thumbnail(item.snippet.thumbnails.default.url),
                    Thumbnail(item.snippet.thumbnails.medium.url),
                    Thumbnail(item.snippet.thumbnails.high.url)
                )
            )
        }
    }

    override fun subscriptionChannels(): Flow<List<SubscriptionChannel>> {
        return subscriptionChannels
    }
}
