package com.sys1yagi.channel_list.presentation.page.channelist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator
import com.sys1yagi.channel_list.presentation.component.SwipeToRefreshLayout

@Composable
fun ChannelListPage(onClickChannel: (SubscriptionChannel) -> Unit) {
    val viewModel: ChannelListPageViewModel = getViewModel()
    val viewState = viewModel.state.collectAsState()

    ChannelListDisplay(viewState.value, onClickChannel) {
        viewModel.refresh()
    }
}

@Composable
fun ChannelListDisplay(
    viewState: ChannelListPageViewState,
    onClickChannel: (SubscriptionChannel) -> Unit,
    onRefresh: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (viewState.initializing) {
            CenterCircularProgressIndicator()
        } else {
            SwipeToRefreshLayout(
                refreshingState = viewState.swipeRefreshing,
                onRefresh = onRefresh,
                refreshIndicator = {
                    Surface(elevation = 10.dp, shape = CircleShape) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .preferredSize(36.dp)
                                .padding(4.dp)
                        )
                    }
                },
            ) {
                ChannelList(viewState.subscriptionChannels, onClickChannel)
            }
        }
    }
}


@Preview
@Composable
fun ChannelListDisplayPreviewLoaded() {
    ChannelListDisplay(
        ChannelListPageViewState(
            initializing = false,
            false,
            0.until(10).map {
                SubscriptionChannel(
                    title = "HikakinTV",
                    description = "HikakinTVはヒカキンが日常の面白いものを紹介するチャンネルです。\n◆プロフィール◆\nYouTubeにてHIKAKIN、HikakinTV、HikakinGames、HikakinBlogと\n４つのチャンネルを運営し、動画の総アクセス数は100億回を突破、\nチャンネル登録者数は計2000万人以上、YouTubeタレント事務所uuum株式会社ファウンダー兼最高顧問。",
                    channelId = "UCZf__ehlCEBPop-_sldpBUQ",
                    thumbnails = Thumbnails(
                        default = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s88-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                        medium = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s240-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                        high = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s800-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                    )
                )
            }
        ),
        {},
        {}
    )
}

@Preview
@Composable
fun ChannelListDisplayPreviewLoading() {
    ChannelListDisplay(
        ChannelListPageViewState(
            initializing = true
        ),
        {},
        {}
    )
}
