package com.sys1yagi.channel_list

import androidx.multidex.MultiDexApplication
import com.chibatching.kotpref.Kotpref
import com.sys1yagi.channel_list.di.mainActivity
import com.sys1yagi.channel_list.di.singleton
import com.sys1yagi.channel_list.di.viewModels
import com.sys1yagi.channel_list.infrastracture.preference.SubscriptionChannelPref
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        Kotpref.init(this)

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    singleton,
                    mainActivity,
                    viewModels
                )
            )
        }
    }
}
