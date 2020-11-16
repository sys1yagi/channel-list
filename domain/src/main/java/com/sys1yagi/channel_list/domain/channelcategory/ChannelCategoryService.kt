package com.sys1yagi.channel_list.domain.channelcategory

import com.sys1yagi.channel_list.domain.category.CategoryRepository
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ChannelCategoryService(
    private val categoryRepository: CategoryRepository,
    private val channelCategoryRepository: ChannelCategoryRepository
) {
    suspend fun stateOfChannelCategories(channel: SubscriptionChannel): Flow<ChannelCategories> {
        return combine(
            channelCategoryRepository.subscribe(channel.channelId),
            categoryRepository.subscribe()
        ) { assignedList, categories ->
            ChannelCategories(
                channel,
                categories.map { category ->
                    AssignedCategory(
                        channel,
                        category,
                        assignedList.any { it.id == category.id }
                    )
                }
            )
        }
    }

    suspend fun assign(assignedCategory: AssignedCategory) {
        channelCategoryRepository.addCategory(
            assignedCategory.channel.channelId,
            assignedCategory.category
        )
    }

    suspend fun unassign(assignedCategory: AssignedCategory) {
        channelCategoryRepository.deleteCategory(
            assignedCategory.channel.channelId,
            assignedCategory.category
        )
    }
}
