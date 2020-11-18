package com.sys1yagi.channel_list.presentation.component

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sys1yagi.channel_list.MainActivity
import com.sys1yagi.channel_list.presentation.ChannellistTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationUpTopAppBarKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun title() {
        composeTestRule.setContent {
            ChannellistTheme {
                NavigationUpTopAppBar("app bar title") {}
            }
        }
        composeTestRule.onNodeWithText("app bar title").assertExists()
    }

    @Test
    fun onBack() {
        var clicked = false
        composeTestRule.setContent {
            ChannellistTheme {
                NavigationUpTopAppBar("title") {
                    clicked = true
                }
            }
        }

        composeTestRule.onNodeWithText("back button").performClick()
        assertThat(clicked).isTrue()
    }
}
