package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionChannelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg subscriptionChannels: SubscriptionChannelEntity)

    @Delete
    suspend fun delete(vararg subscriptionChannels: SubscriptionChannelEntity)

    @Query("SELECT * FROM subscription_channels")
    fun subscribe(): Flow<List<SubscriptionChannelEntity>>

    @Query("SELECT * FROM subscription_channels")
    fun all(): List<SubscriptionChannelEntity>

    @Query("SELECT count(0) FROM subscription_channels")
    suspend fun count(): Long

    @Query("SELECT * FROM subscription_channels where channelId = :channelId")
    suspend fun findByChannelId(channelId: String): SubscriptionChannelEntity?
}
