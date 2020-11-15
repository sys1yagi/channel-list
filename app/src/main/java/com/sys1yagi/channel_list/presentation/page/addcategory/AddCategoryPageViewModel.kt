package com.sys1yagi.channel_list.presentation.page.addcategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.category.CategoryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddCategoryPageViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddCategoryPageViewState())

    val state: StateFlow<AddCategoryPageViewState> = _state

    fun addCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.addCategory(category)
            _state.value = AddCategoryPageViewState(saved = true)
            delay(1000)
            _state.value = AddCategoryPageViewState(closed = true)
        }
    }
}

data class AddCategoryPageViewState(
    val saved: Boolean = false,
    val closed: Boolean = false,
)
