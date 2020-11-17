package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        SubscriptionChannelEntity::class,
        CategoryEntity::class,
        ChannelCategoryEntity::class,
        CategoryVideoListLastSyncDateEntity::class,
        VideoEntity::class,
        VideoCategoryMappingEntity::class,
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun subscriptionChannelDao(): SubscriptionChannelDao
    abstract fun categoryDao(): CategoryDao
    abstract fun channelCategoryDao(): ChannelCategoryDao
    abstract fun categoryVideoListLastSyncDateDao(): CategoryVideoListLastSyncDateDao
    abstract fun videoDao(): VideoDao
    abstract fun videoCategoryMappingDao(): VideoCategoryMappingDao
}
