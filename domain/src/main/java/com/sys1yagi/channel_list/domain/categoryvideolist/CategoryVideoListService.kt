package com.sys1yagi.channel_list.domain.categoryvideolist

import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.ZoneOffset

class CategoryVideoListService(
    private val categoryVideoListRepository: CategoryVideoListRepository
) {
    suspend fun videoListByCategoryId(
        categoryId: Id<Category>
    ): Flow<List<Video>> {
        val lastSyncDate = categoryVideoListRepository.lastSyncDateBy(categoryId)
        val elapsedTime =
            LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - (lastSyncDate?.toEpochSecond(
                ZoneOffset.UTC
            ) ?: 0)
        if (lastSyncDate == null || elapsedTime > 24 * 60 * 60) {
            categoryVideoListRepository.syncCategoryVideoLis(categoryId)
        }
        return categoryVideoListRepository.categoryVideoListBy(categoryId)
    }

    suspend fun refresh(categoryId: Id<Category>) {
        categoryVideoListRepository.syncCategoryVideoLis(categoryId)
    }
}
