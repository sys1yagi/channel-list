package com.sys1yagi.channel_list.infrastracture.repository

import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.category.CategoryRepository
import com.sys1yagi.channel_list.domain.category.CategoryWithAssignedChannelCount
import com.sys1yagi.channel_list.infrastracture.database.CategoryDao
import com.sys1yagi.channel_list.infrastracture.database.CategoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseCategoryRepository(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override suspend fun subscribe(): Flow<List<CategoryWithAssignedChannelCount>> {
        return categoryDao.subscribe().map {
            it.map {
                CategoryWithAssignedChannelCount(it.categoryEntity.toModel(), it.count)
            }
        }
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.insert(
            CategoryEntity(
                category.id?.value ?: 0,
                category.name
            )
        )
    }

    override suspend fun deleteCategory(category: Category) {
        categoryDao.delete(
            CategoryEntity(
                category.id?.value ?: 0,
                category.name
            )
        )
    }
}
