package com.sys1yagi.channel_list.domain.subscriptionchannel

import kotlinx.coroutines.flow.Flow

class SubscriptionChannelService(
    private val subscriptionChannelRepository: SubscriptionChannelRepository
) {
    suspend fun subscriptionChannels(): Flow<List<SubscriptionChannel>> {
        val lastSyncDate = subscriptionChannelRepository.lastSyncDate()
        if (lastSyncDate == null) {
            subscriptionChannelRepository.syncSubscriptionChannels()
        }
        return subscriptionChannelRepository.subscriptionChannels()
    }

    suspend fun refresh() {
        subscriptionChannelRepository.syncSubscriptionChannels()
    }
}
