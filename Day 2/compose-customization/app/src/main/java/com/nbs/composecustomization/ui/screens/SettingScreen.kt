package com.nbs.composecustomization.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.nbs.composecustomization.R
import com.nbs.composecustomization.ui.organisms.MenuResourceItemAttribute
import com.nbs.composecustomization.ui.organisms.SettingMenuSectionAttribute

private data class SettingScreenState(
    @DrawableRes val userAvatar: Int = 0,
    val userName: String = "",
    val userEmail: String = "",
    val settingMenuList: List<SettingMenuSectionAttribute> = emptyList(),
)

private sealed class SettingScreenEvent {
    data object OnClickBack : SettingScreenEvent()
    data object OnClickLogout : SettingScreenEvent()
}

@Composable
private fun SettingScreen(
    state: SettingScreenState = SettingScreenState(),
    event: (SettingScreenEvent) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        containerColor = Color.White,
        topBar = {
            // TODO 1: Top Bar
        },
        content = {
            // TODO 2: Content
            SettingScreenContent(
                modifier = Modifier.padding(it),
                state = state,
            )
        },
        bottomBar = {
            // TODO 3: Bottom Bar
        },
    )
}

@Composable
private fun SettingScreenContent(modifier: Modifier, state: SettingScreenState) {
    // TODO 2: Content
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Setting Screen")
    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    SettingScreen(
        state = SettingScreenState(
            userAvatar = R.drawable.img_man_side_view,
            userName = "Alex Doe",
            userEmail = "alex.doe@email.com",
            settingMenuList = listOf(
                SettingMenuSectionAttribute(
                    title = "PERSONAL INFORMATION",
                    itemList = listOf(
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Contact Details",
                            description = "Manage your phone and email",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Mailing Address",
                        ),
                    ),
                ),
                SettingMenuSectionAttribute(
                    title = "SECURITY",
                    itemList = listOf(
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Change Password",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Manage Biometrics",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Linked Accounts & Devices",
                        ),
                    ),
                ),
                SettingMenuSectionAttribute(
                    title = "APP PREFERENCES",
                    itemList = listOf(
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Notification",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = R.drawable.ic_setting,
                            label = "Theme",
                        ),
                    ),
                ),
            ),
        ),
    )
}