package com.sys1yagi.channel_list.infrastracture.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category

@Entity(
    tableName = "categories"
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
) {
    fun toModel() = Category(
        Id(id),
        name
    )
}
