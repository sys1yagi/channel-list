package com.sys1yagi.channel_list.presentation.page.categoryvideolist

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.sys1yagi.channel_list.R
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.Id
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.categoryvideolist.Video
import com.sys1yagi.channel_list.domain.categoryvideolist.VideoThumbnail
import com.sys1yagi.channel_list.domain.categoryvideolist.VideoThumbnails
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator
import com.sys1yagi.channel_list.presentation.component.NavigationUpTopAppBar
import com.sys1yagi.channel_list.presentation.component.SwipeToRefreshLayout
import com.sys1yagi.channel_list.presentation.typography
import org.koin.core.parameter.parametersOf
import java.time.ZonedDateTime

@Composable
fun CategoryVideoListPage(
    categoryId: Id<Category>,
    categoryName: String,
    onBack: () -> Unit,
) {
    val viewModel: CategoryVideoListPageViewModel = getViewModel { parametersOf(categoryId) }
    val viewState = viewModel.state.collectAsState()
    val context = ContextAmbient.current

    CategoryVideoListDisplay(
        categoryName,
        viewState.value,
        { video ->
            val uri = Uri.parse("https://www.youtube.com/watch?v=${video.videoId}")
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    uri
                )
            )
        },
        {
            viewModel.refresh()
        },
        onBack
    )
}

@Composable
fun CategoryVideoListDisplay(
    categoryName: String,
    viewState: CategoryVideoListPageViewState,
    onClickVideo: (Video) -> Unit,
    onRefresh: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            NavigationUpTopAppBar(
                stringResource(R.string.category_video_list, categoryName),
                onBack
            )
        }
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
                    CategoryVideoList(viewState.videoList, onClickVideo)
                }
            }
        }
    }
}

@Composable
fun CategoryVideoList(
    videoList: List<Video>,
    onClickVideo: (Video) -> Unit
) {
    Scaffold(
    ) {
        Surface(Modifier.fillMaxSize()) {
            if (videoList.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("動画はまだありません", style = typography.subtitle1)
                }
            } else {
                LazyColumn {
                    items(videoList) { video ->
                        Column {
                            VideoCard(video, onClickVideo)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewEmpty() {
    ChannellistTheme {
        CategoryVideoList(emptyList()) {

        }
    }
}

@Preview
@Composable
fun Preview() {
    val thumbnail = VideoThumbnail(
        "https://i.ytimg.com/vi/jSwaR7dXTFY/default.jpg",
        120,
        90,
    )
    CategoryVideoList(
        listOf(
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
        )
    ) {

    }
}
