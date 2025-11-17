package com.nbs.composecustomization.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.composecustomization.R
import com.nbs.composecustomization.ui.organisms.HeaderPage
import com.nbs.composecustomization.ui.organisms.MenuResourceItemAttribute
import com.nbs.composecustomization.ui.organisms.ProfileComponent
import com.nbs.composecustomization.ui.organisms.ProfileComponentAttribute
import com.nbs.composecustomization.ui.organisms.SettingMenuSection
import com.nbs.composecustomization.ui.organisms.SettingMenuSectionAttribute
import com.nbs.composecustomization.ui.theme.colorOnPrimary
import com.nbs.composecustomization.ui.theme.colorOnTertiaryContainer


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
            HeaderPage(
                title = "Settings",
                leadingIconRes = R.drawable.ic_arrow_left,
                onClickLeadingIcon = {
                    event(SettingScreenEvent.OnClickBack)
                },
            )
        },
        content = {
            SettingScreenContent(
                modifier = Modifier.padding(it),
                state = state,
            )
        },
        bottomBar = {
            SettingScreenBottomBar(event = event)
        },
    )
}

@Composable
private fun SettingScreenContent(modifier: Modifier, state: SettingScreenState) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            ProfileComponent(
                attribute = ProfileComponentAttribute(
                    avatarImageRes = state.userAvatar,
                    userName = state.userName,
                    userEmail = state.userEmail,
                ),
            )
        }

        items(state.settingMenuList) { section ->
            SettingMenuSection(attribute = section)
        }
    }
}

@Composable
private fun SettingScreenBottomBar(event: (SettingScreenEvent) -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = CircleShape,
        border = BorderStroke(width = 1.dp, color = colorOnTertiaryContainer),
        colors = ButtonDefaults.buttonColors(containerColor = colorOnPrimary),
        onClick = {
            event(SettingScreenEvent.OnClickLogout)
        },
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(R.drawable.ic_logout_outline),
                contentDescription = null,
                tint = Color.Red,
            )
            Text(text = "Logout", color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
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
