package com.sys1yagi.channel_list.infrastracture.preference

import com.chibatching.kotpref.KotprefModel

object SubscriptionChannelPref : KotprefModel() {
    var lastSyncDate by longPref(-1L)
}
