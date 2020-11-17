package com.sys1yagi.channel_list.presentation.page.categoryvideolist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.domain.categoryvideolist.Video
import com.sys1yagi.channel_list.domain.categoryvideolist.VideoThumbnail
import com.sys1yagi.channel_list.domain.categoryvideolist.VideoThumbnails
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import com.sys1yagi.channel_list.presentation.typography
import dev.chrisbanes.accompanist.coil.CoilImage
import java.time.ZonedDateTime

@Composable
fun VideoCard(video: Video, onClickVideo: (Video) -> Unit) {
    val height = 220.dp
    Column(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = {onClickVideo(video)})
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(height)
                .align(Alignment.CenterHorizontally)
        ) {
            CoilImage(
                data = video.thumbnails.standard.url,
                contentScale = ContentScale.Crop,
                loading = {
                    Column(
                        modifier = Modifier.fillMaxWidth().then(Modifier.height(height)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "loading...", style = typography.overline)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
            )
        }
        Column(Modifier.padding(8.dp)) {
            Text(text = video.title, style = typography.body1)
            Column(
                Modifier.padding(top = 4.dp).fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = video.publishedAt.toString(), style = typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PreviewVideoCard() {
    val thumbnail = VideoThumbnail(
        "https://i.ytimg.com/vi/jSwaR7dXTFY/sddefault.jpg",
        120,
        90,
    )
    ChannellistTheme {
        VideoCard(
            Video(
                channelId = "UCZf__ehlCEBPop-_sldpBUQ",
                title = "【PS5】プレステ５がキター！開封してテレビ繋いだら画質良すぎ&コントローラー振動ヤバすぎ…【ヒカキンTV】",
                description = "◆PS5フォートナイト遊んでみた！\nhttps://youtu.be/NosEKdcHXJE\n\n◆チャンネル登録はこちら↓\nhttp://www.youtube.com/user/hikakintv?sub_confirmation=1\n\n◆ツイッター\nhttps://twitter.com/hikakin\n\n◆インスタグラム\nhttps://instagram.com/hikakin/\n\n◆TikTok\nhttps://vt.tiktok.com/BTWxUN/\n\n◆ヒカキンゲームズ\nhttp://www.youtube.com/hikakingames\n\n◆ビートボックス動画のHIKAKINチャンネル\nhttp://www.youtube.com/HIKAKIN\n\n◆ラフな動画のHikakinBlog\nhttp://www.youtube.com/hikakinblog\n\n\n◆ヒカキンLINEスタンプはこちら\nhttps://store.line.me/stickershop/product/1022677/ja\n\n\n◆ヒカキンLINE公式アカウント\n●友達登録はこちら↓\nhttp://line.naver.jp/ti/p/%40hikakin\n\n#PS5\n#プレステ5\n#ソフト",
                playlistId = "UUZf__ehlCEBPop-_sldpBUQ",
                publishedAt = ZonedDateTime.parse("2020-11-17T08:53:20Z"),
                videoId = "jSwaR7dXTFY",
                thumbnails = VideoThumbnails(
                    thumbnail,
                    thumbnail,
                    thumbnail,
                    thumbnail,
                    thumbnail
                )
            )
        ){}
    }
}
