package com.sys1yagi.channel_list.presentation.page.categoryvideolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.categoryvideolist.CategoryVideoListService
import com.sys1yagi.channel_list.domain.categoryvideolist.Video
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoryVideoListPageViewModel(
    private val categoryId: Id<Category>,
    private val categoryVideoListService: CategoryVideoListService
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryVideoListPageViewState())

    val state: StateFlow<CategoryVideoListPageViewState> = _state

    init {
        viewModelScope.launch {
            categoryVideoListService.videoListByCategoryId(categoryId).collect {
                _state.value = CategoryVideoListPageViewState(
                    false,
                    false,
                    it
                )
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = CategoryVideoListPageViewState(
                false,
                true,
                _state.value.videoList
            )
            categoryVideoListService.refresh(categoryId)
        }
    }
}

data class CategoryVideoListPageViewState(
    val initializing: Boolean = true,
    val swipeRefreshing: Boolean = false,
    val videoList: List<Video> = emptyList()
)
