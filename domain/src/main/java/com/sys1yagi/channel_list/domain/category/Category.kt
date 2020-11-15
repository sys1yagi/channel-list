package com.sys1yagi.channel_list.domain.category

import com.sys1yagi.channel_list.domain.Id

data class Category(
    val id: Id<Category>,
    val name: String
)
