package com.sys1yagi.channel_list.domain.categoryvideolist

data class VideoThumbnails(
    val default: VideoThumbnail,
    val medium: VideoThumbnail,
    val high: VideoThumbnail,
    val standard: VideoThumbnail,
    val maxres: VideoThumbnail
)
