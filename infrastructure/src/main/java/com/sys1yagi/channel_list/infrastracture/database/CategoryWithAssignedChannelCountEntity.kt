package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Embedded

class CategoryWithAssignedChannelCountEntity {

    @Embedded
    lateinit var categoryEntity: CategoryEntity

    var count: Int = 0
}
