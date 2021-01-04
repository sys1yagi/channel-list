package com.sys1yagi.channel_list.presentation.page.channelist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.presentation.component.ChannelCard

@Composable
fun ChannelList(subscriptionChannels: List<SubscriptionChannel>, onClickChannel: (SubscriptionChannel) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Surface(modifier = Modifier.padding(8.dp)) {
                Text("${subscriptionChannels.size}件")
            }
        }
        items(subscriptionChannels) { item ->
            ChannelCard(item, onClick = onClickChannel)
        }
    }
}

@Preview
@Composable
fun ChannelListPreview() {
    ChannelList(
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
    ){}
}
