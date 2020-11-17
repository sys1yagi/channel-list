package com.sys1yagi.channel_list.domain.categoryvideolist

import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface CategoryVideoListRepository {
    suspend fun lastSyncDateBy(categoryId: Id<Category>): LocalDateTime?
    suspend fun syncCategoryVideoLis(categoryId: Id<Category>)

    fun categoryVideoListBy(categoryId: Id<Category>): Flow<List<Video>>
}
