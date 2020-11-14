package com.sys1yagi.channel_list.presentation.page.channelist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator

@Composable
fun ChannelListPage() {
    val viewModel: ChannelListPageViewModel = getViewModel()
    val viewState = viewModel.state.collectAsState()

    ChannelListDisplay(viewState.value)
}

@Composable
fun ChannelListDisplay(viewState: ChannelListPageViewState) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (viewState.refreshing) {
            CenterCircularProgressIndicator()
        } else {
            ChannelList(viewState.subscriptionChannels)
        }
    }
}

@Composable
fun ChannelList(subscriptionChannels: List<SubscriptionChannel>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(subscriptionChannels) { item ->
            ChannelCard(item) {

            }
        }
    }
}


@Preview
@Composable
fun ChannelListDisplayPreviewLoading() {
    ChannelListDisplay(
        ChannelListPageViewState(
            refreshing = false,
            listOf(
                SubscriptionChannel(
                    title = "HikakinTV",
                    description = "HikakinTVはヒカキンが日常の面白いものを紹介するチャンネルです。\\n◆プロフィール◆\\nYouTubeにてHIKAKIN、HikakinTV、HikakinGames、HikakinBlogと\\n４つのチャンネルを運営し、動画の総アクセス数は100億回を突破、\\nチャンネル登録者数は計2000万人以上、YouTubeタレント事務所uuum株式会社ファウンダー兼最高顧問。",
                    channelId = "UC-3kXCIPZSqF8D4T4Bjkndg",
                    thumbnails = Thumbnails(
                        default = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s88-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                        medium = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s240-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                        high = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s800-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                    )
                )
            )
        )
    )
}

@Preview
@Composable
fun ChannelListDisplayPreviewLoaded() {
    ChannelListDisplay(
        ChannelListPageViewState(
            refreshing = true
        )
    )
}
