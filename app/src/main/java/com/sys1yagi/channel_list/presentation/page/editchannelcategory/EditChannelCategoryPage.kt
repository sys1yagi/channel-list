package com.sys1yagi.channel_list.presentation.page.editchannelcategory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sys1yagi.channel_list.R
import com.sys1yagi.channel_list.di.getViewModel
import com.sys1yagi.channel_list.domain.category.Category
import com.sys1yagi.channel_list.domain.channelcategory.AssignedCategory
import com.sys1yagi.channel_list.domain.subscriptionchannel.SubscriptionChannel
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnail
import com.sys1yagi.channel_list.domain.subscriptionchannel.Thumbnails
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import com.sys1yagi.channel_list.presentation.component.CenterCircularProgressIndicator
import com.sys1yagi.channel_list.presentation.component.ChannelCard
import com.sys1yagi.channel_list.presentation.component.NavigationUpTopAppBar
import com.sys1yagi.channel_list.presentation.typography
import org.koin.core.parameter.parametersOf

@Composable
fun EditChannelCategoryPage(
    channelId: String,
    onAddCategory: () -> Unit,
    onBack: () -> Unit,
) {
    val viewModel: EditChannelCategoryPageViewModel = getViewModel { parametersOf(channelId) }
    val viewState = viewModel.state.collectAsState()

    EditChannelCategoryDisplay(
        viewState.value, onAddCategory,
        {
            viewModel.updateAssignedCategory(it)
        },
        onBack
    )
}

@Composable
fun EditChannelCategoryDisplay(
    viewState: EditChannelCategoryPageViewState,
    onAddCategory: () -> Unit,
    onCheckAssignedCategory: (AssignedCategory) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            NavigationUpTopAppBar(
                stringResource(R.string.edit_channel_category),
                onBack
            )
        }
    ) {
        Surface(modifier = Modifier.padding(8.dp)) {
            val subscriptionChannel = viewState.subscriptionChannel
            val categories = viewState.categories
            if (subscriptionChannel == null || categories == null) {
                CenterCircularProgressIndicator()
            } else {
                LazyColumn(

                ) {
                    item {
                        ChannelCard(subscriptionChannel = subscriptionChannel,
                            truncateSize = -1,
                            enabled = false,
                            onClick = { /* no op */ }
                        )
                    }
                    if (categories.isEmpty()) {
                        item {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .then(Modifier.padding(top = 16.dp)),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text("カテゴリはまだありません", style = typography.subtitle1)
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = onAddCategory) {
                                    Text("カテゴリを追加する", style = typography.body1)
                                }
                            }
                        }
                    } else {
                        items(categories.size) { i ->
                            val assignedCategory = categories[i]
                            AssignedCategoryCard(assignedCategory) {
                                onCheckAssignedCategory(
                                    AssignedCategory(
                                        subscriptionChannel,
                                        assignedCategory.category,
                                        !assignedCategory.assigned
                                    )
                                )
                            }
                        }
                        item {
                            Column(
                                Modifier.fillMaxWidth().then(Modifier.padding(top = 16.dp)),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Button(onClick = onAddCategory) {
                                    Text("カテゴリを追加する", style = typography.body1)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCategoryIsEmpty() {
    ChannellistTheme {
        EditChannelCategoryDisplay(
            viewState =
            EditChannelCategoryPageViewState(
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
                emptyList()
            ),
            {},
            {},
            {}
        )
    }
}

@Preview
@Composable
fun PreviewCategory() {
    ChannellistTheme {
        val channel = SubscriptionChannel(
            title = "HikakinTV",
            description = "HikakinTVはヒカキンが日常の面白いものを紹介するチャンネルです。\n◆プロフィール◆\nYouTubeにてHIKAKIN、HikakinTV、HikakinGames、HikakinBlogと\n４つのチャンネルを運営し、動画の総アクセス数は100億回を突破、\nチャンネル登録者数は計2000万人以上、YouTubeタレント事務所uuum株式会社ファウンダー兼最高顧問。",
            channelId = "UCZf__ehlCEBPop-_sldpBUQ",
            thumbnails = Thumbnails(
                default = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s88-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                medium = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s240-c-k-no-mo-rj-c0xffffff/photo.jpg"),
                high = Thumbnail("https://yt3.ggpht.com/-NFhw6-eus8Y/AAAAAAAAAAI/AAAAAAAAAAA/rtPbnb9gvAQ/s800-c-k-no-mo-rj-c0xffffff/photo.jpg"),
            )
        )
        EditChannelCategoryDisplay(
            viewState =
            EditChannelCategoryPageViewState(
                channel,
                listOf(
                    AssignedCategory(
                        channel,
                        Category(null, "マラソン"),
                        true
                    ),
                    AssignedCategory(
                        channel,
                        Category(null, "ゲーム"),
                        false
                    )
                )
            ),
            {},
            {},
            {}
        )
    }
}
