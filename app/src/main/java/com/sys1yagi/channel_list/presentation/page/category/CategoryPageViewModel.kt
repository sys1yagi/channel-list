package com.sys1yagi.channel_list.presentation.page.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.category.CategoryRepository
import com.sys1yagi.channel_list.domain.category.CategoryWithAssignedChannelCount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CategoryPageViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryPageViewState())

    val state: StateFlow<CategoryPageViewState> = _state

    init {
        viewModelScope.launch {
            categoryRepository.subscribe().collect { categories ->
                _state.value = CategoryPageViewState(
                    false,
                    categories
                )
            }
        }
    }
}

data class CategoryPageViewState(
    val initializing: Boolean = true,
    val categories: List<CategoryWithAssignedChannelCount> = emptyList()
)
