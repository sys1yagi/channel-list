package com.sys1yagi.channel_list.domain.categoryvideolist

import java.time.ZonedDateTime

data class Video (
    val videoId: String,
    val channelId: String,
    val playlistId: String,
    val publishedAt: ZonedDateTime,
    val title: String,
    val description: String,
    val thumbnails: VideoThumbnails
)
