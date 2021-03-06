package com.sys1yagi.channel_list.presentation.page.channelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChannelListPageViewModel(
    private val subscriptionChannelService: SubscriptionChannelService
) : ViewModel() {

    private val _state = MutableStateFlow(ChannelListPageViewState())

    val state: StateFlow<ChannelListPageViewState> = _state

    init {
        viewModelScope.launch {
            subscriptionChannelService.subscriptionChannels().collect {
                _state.value = ChannelListPageViewState(
                    false,
                    false,
                    it
                )
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = ChannelListPageViewState(
                false,
                true,
                _state.value.subscriptionChannels
            )
            subscriptionChannelService.refresh()
        }
    }
}

data class ChannelListPageViewState(
    val initializing: Boolean = true,
    val swipeRefreshing: Boolean = false,
    val subscriptionChannels: List<SubscriptionChannel> = emptyList()
)
