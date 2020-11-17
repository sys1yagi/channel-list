package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "video_category_mapping",
    primaryKeys = ["categoryId", "videoId"],
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = VideoEntity::class,
            parentColumns = ["videoId"],
            childColumns = ["videoId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class VideoCategoryMappingEntity(
    val categoryId: Long,
    val videoId: String
)
