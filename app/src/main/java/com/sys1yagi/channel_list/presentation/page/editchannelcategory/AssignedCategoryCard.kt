package com.sys1yagi.channel_list.presentation.page.editchannelcategory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.channelcategory.AssignedCategory
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.presentation.typography

@Composable
fun AssignedCategoryCard(category: AssignedCategory, onCheckedChange: (Boolean) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
            .then(Modifier.fillMaxWidth())
            .then(Modifier.clickable(onClick = {
                onCheckedChange(!category.assigned)
            })),
        elevation = 0.dp
    ) {
        Surface(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Checkbox(
                    checked = category.assigned,
                    onCheckedChange = onCheckedChange
                )
                Text(
                    category.category.name,
                    style = typography.h6,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewUnchecked() {
    AssignedCategoryCard(
        AssignedCategory(
            SubscriptionChannel(
                title = "HikakinTV",
                description = "HikakinTVはヒカキンが日常の面白いものを紹介するチャンネルです。\n◆プロフィール◆\nYouTubeにてHIKAKIN、HikakinTV、HikakinGames、HikakinBlogと\n４つのチャンネルを運営し、動画の総アクセス数は100億回を突破、\nチャンネル登録者数は計2000万人以上、YouTubeタレント事務所uuum株式会社ファウンダー兼最高顧問。",
                channelId = "UCZf__ehlCEBPop-_sldpBUQ",
                thumbnails = Thumbnails(
                    default = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s88-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                    medium = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s240-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                    high = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s800-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                )
            ),
            Category(null, "マラソン"),
            false
        )
    ) {}
}

@Preview
@Composable
fun PreviewChecked() {
    AssignedCategoryCard(
        AssignedCategory(
            SubscriptionChannel(
                title = "HikakinTV",
                description = "HikakinTVはヒカキンが日常の面白いものを紹介するチャンネルです。\n◆プロフィール◆\nYouTubeにてHIKAKIN、HikakinTV、HikakinGames、HikakinBlogと\n４つのチャンネルを運営し、動画の総アクセス数は100億回を突破、\nチャンネル登録者数は計2000万人以上、YouTubeタレント事務所uuum株式会社ファウンダー兼最高顧問。",
                channelId = "UCZf__ehlCEBPop-_sldpBUQ",
                thumbnails = Thumbnails(
                    default = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s88-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                    medium = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s240-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                    high = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s800-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                )
            ),
            Category(null, "マラソン"),
            true
        )
    ){}
}
