package com.sys1yagi.channel_list.infrastracture.repository

import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.channelcategory.ChannelCategoryRepository
import com.sys1yagi.channel_list.infrastracture.database.ChannelCategoryDao
import com.sys1yagi.channel_list.infrastracture.database.ChannelCategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseChannelCategoryRepository(
    private val channelCategoryDao: ChannelCategoryDao
) : ChannelCategoryRepository {
    override suspend fun subscribe(channelId: String): Flow<List<Category>> {
        return channelCategoryDao.subscribe(channelId).map {
            it.map { it.category.toModel() }
        }
    }

    override suspend fun addCategory(channelId: String, category: Category) {
        channelCategoryDao.insert(
            ChannelCategoryEntity(channelId, category.id!!.value)
        )
    }

    override suspend fun deleteCategory(channelId: String, category: Category) {
        channelCategoryDao.delete(
            ChannelCategoryEntity(channelId, category.id!!.value)
        )
    }
}
