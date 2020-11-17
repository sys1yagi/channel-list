package com.sys1yagi.channel_list.di

import androidx.activity.ComponentActivity
import androidx.room.Room
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.ExponentialBackOff
import com.google.api.services.youtube.YouTube
import com.google.api.services.youtube.YouTubeScopes
import com.sys1yagi.channel_list.GlobalViewModel
import com.sys1yagi.channel_list.MainActivity
import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.auth.AuthenticationRepository
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.category.CategoryRepository
import com.sys1yagi.channel_list.domain.categoryvideolist.CategoryVideoListRepository
import com.sys1yagi.channel_list.domain.categoryvideolist.CategoryVideoListService
import com.sys1yagi.channel_list.domain.channelcategory.ChannelCategoryRepository
import com.sys1yagi.channel_list.domain.channelcategory.ChannelCategoryService
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelRepository
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannelService
import com.sys1yagi.channel_list.infrastracture.database.CategoryDao
import com.sys1yagi.channel_list.infrastracture.database.Database
import com.sys1yagi.channel_list.infrastracture.database.SubscriptionChannelDao
import com.sys1yagi.channel_list.infrastracture.repository.*
import com.sys1yagi.channel_list.presentation.page.addcategory.AddCategoryPageViewModel
import com.sys1yagi.channel_list.presentation.page.category.CategoryPageViewModel
import com.sys1yagi.channel_list.presentation.page.categoryvideolist.CategoryVideoListPageViewModel
import com.sys1yagi.channel_list.presentation.page.channelist.ChannelListPageViewModel
import com.sys1yagi.channel_list.presentation.page.editchannelcategory.EditChannelCategoryPageViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val singleton by lazy {
    module {
        single {
            GoogleAccountCredential.usingOAuth2(
                androidContext(),
                listOf(
                    YouTubeScopes.YOUTUBE_READONLY
                )
            ).apply {
                backOff = ExponentialBackOff()
            }
        }
        single<YouTube> {
            YouTube.Builder(
                NetHttpTransport(),
                JacksonFactory(),
                get<GoogleAccountCredential>(),
            ).setApplicationName("com.sys1yagi.channel_list")
                .build()
        }

        // repository
        single<SubscriptionChannelRepository> {
            YoutubeSubscriptionChannelRepository(
                get(),
                get()
            )
        }
        single<CategoryRepository> {
            DatabaseCategoryRepository(get())
        }
        single<ChannelCategoryRepository> {
            DatabaseChannelCategoryRepository(
                get()
            )
        }
        single<CategoryVideoListRepository> {
            YoutubeCategoryVideoListRepository(
                get(),
                get(),
                get(),
                get(),
                get()
            )
        }

        // service
        single {
            SubscriptionChannelService(get())
        }
        single {
            ChannelCategoryService(get(), get())
        }
        single{
            CategoryVideoListService(get())
        }

        // database
        single {
            Room.databaseBuilder(
                androidApplication(),
                Database::class.java, "database-name"
            ).build()
        }
        single {
            get<Database>().subscriptionChannelDao()
        }
        single {
            get<Database>().categoryDao()
        }
        single {
            get<Database>().channelCategoryDao()
        }
        single {
            get<Database>().videoDao()
        }
        single {
            get<Database>().categoryVideoListLastSyncDateDao()
        }
        single {
            get<Database>().videoCategoryMappingDao()
        }
    }
}

val mainActivity by lazy {
    module {
        scope(named<MainActivity>()) {
            scoped<AuthenticationRepository> { (activity: ComponentActivity) ->
                GoogleAuthenticationRepository(
                    activity,
                    get(),
                    get()
                )
            }
            viewModel { (activity: ComponentActivity) ->
                GlobalViewModel(
                    get { parametersOf(activity) }
                )
            }
        }
    }
}

val viewModels by lazy {
    module {
        viewModel {
            ChannelListPageViewModel(get())
        }
        viewModel {
            CategoryPageViewModel(get())
        }
        viewModel {
            AddCategoryPageViewModel(get())
        }
        viewModel { (channelId: String) ->
            EditChannelCategoryPageViewModel(
                channelId,
                get(),
                get()
            )
        }
        viewModel{ (categoryId: Id<Category>) ->
            CategoryVideoListPageViewModel(
                categoryId,
                get()
            )
        }
    }
}
