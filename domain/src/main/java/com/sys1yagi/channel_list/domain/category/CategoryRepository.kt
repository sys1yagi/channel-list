package com.sys1yagi.channel_list.domain.category

import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun subscribe(): Flow<List<CategoryWithAssignedChannelCount>>
    suspend fun addCategory(category: Category)
    suspend fun deleteCategory(category: Category)
}
