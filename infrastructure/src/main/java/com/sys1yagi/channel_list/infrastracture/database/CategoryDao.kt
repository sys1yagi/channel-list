package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(vararg categories: CategoryEntity)

    @Delete
    suspend fun delete(vararg categories: CategoryEntity)

    @Query("SELECT * FROM categories")
    fun subscribe(): Flow<List<CategoryEntity>>
}
