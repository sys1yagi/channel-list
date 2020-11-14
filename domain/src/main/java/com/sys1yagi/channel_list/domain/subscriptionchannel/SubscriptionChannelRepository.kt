package com.sys1yagi.channel_list.domain.subscriptionchannel

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface SubscriptionChannelRepository {
    suspend fun lastSyncDate(): LocalDateTime?
    suspend fun syncSubscriptionChannels()

    fun subscriptionChannels(): Flow<List<SubscriptionChannel>>
}
