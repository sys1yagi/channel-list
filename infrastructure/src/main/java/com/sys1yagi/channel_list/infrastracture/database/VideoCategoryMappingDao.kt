package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface VideoCategoryMappingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg videoCategoryMappings: VideoCategoryMappingEntity)

    @Delete
    suspend fun delete(vararg videoCategoryMappings: VideoCategoryMappingEntity)
}
