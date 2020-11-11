package com.sys1yagi.channel_list.infrastracture.preference

import com.chibatching.kotpref.KotprefModel

object AuthenticationSetting : KotprefModel() {
    var accountName by nullableStringPref()
    var permitted by booleanPref(false)
}
