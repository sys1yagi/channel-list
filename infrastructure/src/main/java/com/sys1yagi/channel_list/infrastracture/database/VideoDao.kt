package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg videos: VideoEntity)

    @Delete
    suspend fun delete(vararg videos: VideoEntity)

    @Query("select videos.* from videos join video_category_mapping as vcm on videos.videoId = vcm.videoId where vcm.categoryId = :categoryId order by videos.publishedAt desc")
    fun subscribeByCategory(categoryId: Long): Flow<List<VideoEntity>>
}
