package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SubscriptionChannelEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun subscriptionChannelDao(): SubscriptionChannelDao
}
