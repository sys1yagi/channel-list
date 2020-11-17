package com.sys1yagi.channel_list.infrastracture.repository

import com.google.api.services.youtube.YouTube
import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.categoryvideolist.CategoryVideoListRepository
import com.sys1yagi.channel_list.domain.categoryvideolist.Video
import com.sys1yagi.channel_list.infrastracture.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.*

class YoutubeCategoryVideoListRepository(
    private val service: YouTube,
    private val categoryVideoListLastSyncDateDao: CategoryVideoListLastSyncDateDao,
    private val categoryDao: CategoryDao,
    private val videoDao: VideoDao,
    private val videoCategoryMappingDao: VideoCategoryMappingDao
) : CategoryVideoListRepository {
    override suspend fun lastSyncDateBy(categoryId: Id<Category>): LocalDateTime? =
        withContext(Dispatchers.IO) {
            categoryVideoListLastSyncDateDao.findBy(categoryId.value)?.let {
                LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(it.lastSyncDate),
                    ZoneId.systemDefault()
                )
            }
        }

    override suspend fun syncCategoryVideoLis(categoryId: Id<Category>) =
        withContext(Dispatchers.IO) {
            val channelIds = categoryDao.channelsByCategoryId(categoryId.value).map { it.channelId }
            if (channelIds.isEmpty()) {
                return@withContext
            }
            val response = service.channels()
                .list(listOf("snippet", "contentDetails"))
                .setId(channelIds)
                .execute()
            val playlistIds = response.items.map {
                it.contentDetails.relatedPlaylists.uploads
            }

            val maxSize = if (lastSyncDateBy(categoryId) == null) {
                50L
            } else {
                10L
            }

            playlistIds.forEach { playlistId ->
                val playlistItemReponse = service.playlistItems()
                    .list(listOf("snippet", "contentDetails"))
                    .setPlaylistId(playlistId)
                    .setMaxResults(maxSize)
                    .execute()

                val videos = playlistItemReponse.items.map {
                    VideoEntity(
                        videoId = it.contentDetails.videoId,
                        channelId = it.snippet.channelId,
                        playlistId = it.snippet.playlistId,
                        title = it.snippet.title,
                        description = it.snippet.description,
                        publishedAt = ZonedDateTime.parse(it.snippet.publishedAt).toInstant()
                            .toEpochMilli(),
                        thumbnailDefault = it.snippet.thumbnails.default?.url ?: "",
                        thumbnailDefaultWidth = it.snippet.thumbnails.default?.width?.toInt() ?: 0,
                        thumbnailDefaultHeight = it.snippet.thumbnails.default?.height?.toInt()
                            ?: 0,
                        thumbnailMedium = it.snippet.thumbnails.medium?.url ?: "",
                        thumbnailMediumWidth = it.snippet.thumbnails.medium?.width?.toInt() ?: 0,
                        thumbnailMediumHeight = it.snippet.thumbnails.medium?.height?.toInt() ?: 0,
                        thumbnailHigh = it.snippet.thumbnails.high?.url ?: "",
                        thumbnailHighWidth = it.snippet.thumbnails.high?.width?.toInt() ?: 0,
                        thumbnailHighHeight = it.snippet.thumbnails.high?.height?.toInt() ?: 0,
                        thumbnailStandard = it.snippet.thumbnails.standard?.url ?: "",
                        thumbnailStandardWidth = it.snippet.thumbnails.standard?.width?.toInt()
                            ?: 0,
                        thumbnailStandardHeight = it.snippet.thumbnails.standard?.height?.toInt()
                            ?: 0,
                        thumbnailMaxres = it.snippet.thumbnails.maxres?.url ?: "",
                        thumbnailMaxresWidth = it.snippet.thumbnails.maxres?.width?.toInt() ?: 0,
                        thumbnailMaxresHeight = it.snippet.thumbnails.maxres?.height?.toInt() ?: 0,
                    )
                }
                videoDao.insert(*videos.toTypedArray())
                videoCategoryMappingDao.insert(
                    *videos.map {
                        VideoCategoryMappingEntity(
                            categoryId.value,
                            it.videoId
                        )
                    }.toTypedArray()
                )
            }
            categoryVideoListLastSyncDateDao.insert(
                CategoryVideoListLastSyncDateEntity(
                    categoryId = categoryId.value,
                    LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            )
        }

    override fun categoryVideoListBy(categoryId: Id<Category>): Flow<List<Video>> {
        return videoDao.subscribeByCategory(categoryId.value).map {
            it.map {
                it.toModel()
            }
        }
    }
}
