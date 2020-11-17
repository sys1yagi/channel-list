package com.sys1yagi.channel_list.presentation.component

import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.presentation.typography
import com.sys1yagi.channel_list.presentation.util.truncate
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ChannelCard(
    subscriptionChannel: SubscriptionChannel,
    truncateSize: Int = 120,
    enabled: Boolean = true,
    onClick: (SubscriptionChannel) -> Unit,
) {
    Card(
        modifier =
        Modifier.fillMaxWidth()
            .then(
                Modifier.padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
            )
            .then(
                Modifier.clickable(
                    onClick = { onClick(subscriptionChannel) },
                    enabled = enabled
                )
            ),
        elevation = 2.dp
    ) {
        Row(
            Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Box(
                Modifier
                    .width(72.dp)
                    .align(Alignment.Top)
                    .aspectRatio(1f)
            ) {
                CoilImage(
                    data = subscriptionChannel.thumbnails.default.url,
                    contentScale = ContentScale.Crop,
                    loading = { /* TODO do something better here */ },
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium)
                )
            }
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(subscriptionChannel.title, style = typography.h6)
                if (truncateSize > 0) {
                    subscriptionChannel.description.truncate(truncateSize, "...")
                } else {
                    subscriptionChannel.description
                }
                    .split("\n").forEach {
                        Text(it, style = typography.body1)
                    }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    Surface(Modifier.fillMaxWidth()) {
        ChannelCard(
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
        ) {

        }
    }
}
