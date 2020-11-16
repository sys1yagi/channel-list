package com.sys1yagi.channel_list.domain.channelcategory

import com.sys1yagi.channel_list.domain.category.Category
import kotlinx.coroutines.flow.Flow

interface ChannelCategoryRepository {
    suspend fun subscribe(channelId: String): Flow<List<Category>>
    suspend fun addCategory(channelId: String, category: Category)
    suspend fun deleteCategory(channelId: String, category: Category)
}
