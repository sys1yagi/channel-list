package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.*
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(vararg categories: CategoryEntity)

    @Delete
    suspend fun delete(vararg categories: CategoryEntity)

    @Query("select id, name, count(categoryId) as count from categories left outer join channel_categories on channel_categories.categoryId = categories.id group by 1, 2")
    fun subscribe(): Flow<List<CategoryWithAssignedChannelCountEntity>>

    @Query("select sc.* from subscription_channels as sc join channel_categories as cc on sc.channelId = cc.channelId where cc.categoryId == :categoryId")
    fun channelsByCategoryId(categoryId: Long): List<SubscriptionChannelEntity>
}
