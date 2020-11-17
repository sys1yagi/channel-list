package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "category_video_list_last_sync_date",
)
data class CategoryVideoListLastSyncDateEntity(
    @PrimaryKey
    val categoryId: Long,
    val lastSyncDate: Long
)
