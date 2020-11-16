package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg channelCategories: ChannelCategoryEntity)

    @Delete
    suspend fun delete(vararg channelCategories: ChannelCategoryEntity)

    @Transaction
    @Query("SELECT * FROM channel_categories where channelId = :channelId")
    fun subscribe(channelId: String): Flow<List<ChannelCategoryWithValuesEntity>>
}
