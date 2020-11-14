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
    val subscriptionChannelService: SubscriptionChannelService
) : ViewModel() {

    private val _state = MutableStateFlow(ChannelListPageViewState())

    val state: StateFlow<ChannelListPageViewState> = _state

    init {
        viewModelScope.launch {
            subscriptionChannelService.subscriptionChannels().collect {
                _state.value = ChannelListPageViewState(
                    false,
                    it
                )
            }
        }
    }

    // TODO
    override fun onCleared() {
        super.onCleared()
    }
}

data class ChannelListPageViewState(
    val refreshing: Boolean = true,
    val subscriptionChannels: List<SubscriptionChannel> = emptyList()
)
