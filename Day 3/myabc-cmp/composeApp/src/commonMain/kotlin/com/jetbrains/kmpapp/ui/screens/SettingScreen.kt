package com.jetbrains.kmpapp.ui.screens

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
import com.jetbrains.kmpapp.ui.organisms.MenuResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.SettingMenuSectionAttribute
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class SettingScreenState(
    val userAvatar: DrawableResource = Res.drawable.img_man_side_view,
    val userName: String = "",
    val userEmail: String = "",
    val settingMenuList: List<SettingMenuSectionAttribute> = emptyList(),
)

sealed class SettingScreenEvent {
    data object OnClickBack : SettingScreenEvent()
    data object OnClickLogout : SettingScreenEvent()
}

@Composable
fun SettingScreen(
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
fun SettingScreenContent(modifier: Modifier, state: SettingScreenState) {
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
fun SettingScreenPreview() {
    SettingScreen(
        state = SettingScreenState(
            userAvatar = Res.drawable.img_man_side_view,
            userName = "Alex Doe",
            userEmail = "alex.doe@email.com",
            settingMenuList = listOf(
                SettingMenuSectionAttribute(
                    title = "PERSONAL INFORMATION",
                    itemList = listOf(
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Contact Details",
                            description = "Manage your phone and email",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Mailing Address",
                        ),
                    ),
                ),
                SettingMenuSectionAttribute(
                    title = "SECURITY",
                    itemList = listOf(
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Change Password",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Manage Biometrics",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Linked Accounts & Devices",
                        ),
                    ),
                ),
                SettingMenuSectionAttribute(
                    title = "APP PREFERENCES",
                    itemList = listOf(
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Notification",
                        ),
                        MenuResourceItemAttribute(
                            iconRes = Res.drawable.ic_setting,
                            label = "Theme",
                        ),
                    ),
                ),
            ),
        ),
    )
}