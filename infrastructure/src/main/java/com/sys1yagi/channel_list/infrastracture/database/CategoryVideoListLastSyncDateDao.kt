package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryVideoListLastSyncDateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg lastSyncDate: CategoryVideoListLastSyncDateEntity)

    @Delete
    suspend fun delete(vararg lastSyncDate: CategoryVideoListLastSyncDateEntity)

    @Query("select * from category_video_list_last_sync_date where categoryId = :categoryId")
    suspend fun findBy(categoryId: Long): CategoryVideoListLastSyncDateEntity?
}
