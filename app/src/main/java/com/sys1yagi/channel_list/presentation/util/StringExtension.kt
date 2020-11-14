package com.sys1yagi.channel_list.presentation.util

fun String.truncate(max: Int, tail: String): String {
    return if (length > max) {
        take(max) + tail
    } else {
        this
    }
}
