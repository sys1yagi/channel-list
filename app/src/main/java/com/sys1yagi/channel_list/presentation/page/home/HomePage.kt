package com.sys1yagi.channel_list.presentation.page.home

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import com.sys1yagi.channel_list.R
import com.sys1yagi.channel_list.presentation.page.category.CategoryPage
import com.sys1yagi.channel_list.presentation.page.channelist.ChannelListPage
import com.sys1yagi.channel_list.presentation.page.setting.SettingPage

sealed class HomeTab(val route: String, @StringRes val resourceId: Int, val icon: VectorAsset) {
    object Channel : HomeTab("home", R.string.home_subscription_channel, Icons.Filled.List)
    object Category : HomeTab("history", R.string.home_category, Icons.Filled.Category)
    object Setting : HomeTab("setting", R.string.home_setting, Icons.Filled.Settings)
}

@Composable
fun HomePage() {
    val navController = rememberNavController()
    val items = listOf(
        HomeTab.Channel,
        HomeTab.Category,
        HomeTab.Setting,
    )
    Scaffold(
        topBar = {
            TopAppBar(title = {
                val title = stringResource(R.string.app_name)
                Text(title)
            })
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.popBackStack(navController.graph.startDestination, true)

                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) {
        Surface(Modifier.padding(it)) {
            NavHost(navController, startDestination = HomeTab.Channel.route) {
                composable(HomeTab.Channel.route) {
                    ChannelListPage()
                }
                composable(HomeTab.Category.route) {
                    CategoryPage()
                }
                composable(HomeTab.Setting.route) {
                    SettingPage()
                }
            }
        }
    }
}
