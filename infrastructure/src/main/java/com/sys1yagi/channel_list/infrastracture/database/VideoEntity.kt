package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sys1yagi.channel_list.domain.categoryvideolist.Video
import com.sys1yagi.channel_list.domain.categoryvideolist.VideoThumbnail
import com.sys1yagi.channel_list.domain.categoryvideolist.VideoThumbnails
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime


@Entity(
    tableName = "videos",
)
data class VideoEntity(
    @PrimaryKey
    val videoId: String,
    val playlistId: String,
    val channelId: String,
    val publishedAt: Long,
    val title: String,
    val description: String,
    val thumbnailDefault: String,
    val thumbnailDefaultWidth: Int,
    val thumbnailDefaultHeight: Int,
    val thumbnailMedium: String,
    val thumbnailMediumWidth: Int,
    val thumbnailMediumHeight: Int,
    val thumbnailHigh: String,
    val thumbnailHighWidth: Int,
    val thumbnailHighHeight: Int,
    val thumbnailStandard: String,
    val thumbnailStandardWidth: Int,
    val thumbnailStandardHeight: Int,
    val thumbnailMaxres: String,
    val thumbnailMaxresWidth: Int,
    val thumbnailMaxresHeight: Int,
) {
    fun toModel(): Video =
        Video(
            videoId,
            channelId,
            playlistId,
            ZonedDateTime.ofInstant(Instant.ofEpochMilli(publishedAt), ZoneId.systemDefault()),
            title,
            description,
            VideoThumbnails(
                VideoThumbnail(
                    thumbnailDefault,
                    thumbnailDefaultWidth,
                    thumbnailDefaultHeight
                ),
                VideoThumbnail(
                    thumbnailMedium,
                    thumbnailMediumWidth,
                    thumbnailMediumHeight
                ),
                VideoThumbnail(
                    thumbnailHigh,
                    thumbnailHighWidth,
                    thumbnailHighHeight
                ),
                VideoThumbnail(
                    thumbnailStandard,
                    thumbnailStandardWidth,
                    thumbnailStandardHeight
                ),
                VideoThumbnail(
                    thumbnailMaxres,
                    thumbnailMaxresWidth,
                    thumbnailMaxresHeight
                )
            )
        )
}
