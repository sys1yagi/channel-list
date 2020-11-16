package com.sys1yagi.channel_list.presentation.page.editchannelcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.channelcategory.AssignedCategory
import com.sys1yagi.channel_list.domain.channelcategory.ChannelCategoryService
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EditChannelCategoryPageViewModel(
    private val channelId: String,
    private val channelCategoryService: ChannelCategoryService,
    private val subscriptionChannelRepository: SubscriptionChannelRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(EditChannelCategoryPageViewState())

    val state: StateFlow<EditChannelCategoryPageViewState> = _state

    init {
        viewModelScope.launch {
            val channel = subscriptionChannelRepository.findByChannelId(channelId)

            // TODO error
            channel ?: return@launch

            channelCategoryService.stateOfChannelCategories(channel).map {
                EditChannelCategoryPageViewState(
                    it.channel,
                    it.categories
                )
            }.collect {
                _state.value = it
            }
        }
    }

    fun updateAssignedCategory(assignedCategory: AssignedCategory) {
        viewModelScope.launch {
            if (assignedCategory.assigned) {
                channelCategoryService.assign(assignedCategory)
            } else {
                channelCategoryService.unassign(assignedCategory)
            }
        }
    }
}

data class EditChannelCategoryPageViewState(
    val subscriptionChannel: SubscriptionChannel? = null,
    val categories: List<AssignedCategory>? = null
)
