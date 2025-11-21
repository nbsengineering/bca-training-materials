package com.jetbrains.kmpapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.ui.atoms.Avatar
import com.jetbrains.kmpapp.ui.molecules.MenuCardAttribute
import com.jetbrains.kmpapp.ui.molecules.SummaryCard
import com.jetbrains.kmpapp.ui.organisms.BillSection
import com.jetbrains.kmpapp.ui.organisms.BillSectionResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.FavoriteSection
import com.jetbrains.kmpapp.ui.organisms.FavoriteSectionResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.MenuGrid
import com.jetbrains.kmpapp.ui.organisms.MenuGridAttribute
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class DashboardScreenState(
    val userAvatar: DrawableResource = Res.drawable.img_man_side_view,
    val userName: String = "",
    val totalBalance: String = "",
    val accountNumber: String = "",
    val menuList: List<MenuCardAttribute> = emptyList(),
    val paymentList: List<BillSectionResourceItemAttribute> = emptyList(),
    val favoriteList: List<FavoriteSectionResourceItemAttribute> = emptyList(),
)

sealed class DashboardScreenEvent {
    data object OnClickNotification : DashboardScreenEvent()
    data object OnClickMenu : DashboardScreenEvent()
    data object OnClickViewAll : DashboardScreenEvent()
    data object OnClickManage : DashboardScreenEvent()
    data object OnClickRepeat : DashboardScreenEvent()
}

@Composable
fun DashboardScreen(
    state: DashboardScreenState = DashboardScreenState(),
    event: (DashboardScreenEvent) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        containerColor = Color.White,
        topBar = {
            DashboardScreenTopBar(
                userAvatar = state.userAvatar,
                userName = state.userName,
                onClickNotification = {
                    event(DashboardScreenEvent.OnClickNotification)
                },
            )
        },
        content = {
            DashboardScreenContent(
                modifier = Modifier.padding(it),
                state = state,
                event = event,
            )
        },
    )
}

@Composable
private fun DashboardScreenTopBar(
    userAvatar: DrawableResource,
    userName: String,
    onClickNotification: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Avatar(
            imageRes = userAvatar,
            size = 32.dp,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = "Good Morning, $userName",
            fontSize = 18.sp,
        )
        Icon(
            modifier = Modifier.clickable(onClick = onClickNotification),
            painter = painterResource(Res.drawable.ic_notification_fill),
            contentDescription = null,
        )
    }
}

@Composable
private fun DashboardScreenContent(
    modifier: Modifier,
    state: DashboardScreenState,
    event: (DashboardScreenEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            SummaryCard(
                modifier = Modifier.padding(16.dp),
                balance = state.totalBalance,
                accountNumber = state.accountNumber,
            )
        }
        item {
            MenuGrid(
                attribute = MenuGridAttribute(itemList = state.menuList),
                onClickMenu = {
                    event(DashboardScreenEvent.OnClickMenu)
                }
            )
        }
        item {
            BillSection(
                modifier = Modifier.padding(16.dp),
                data = state.paymentList,
                onClickViewAll = {
                    event(DashboardScreenEvent.OnClickViewAll)
                },
            )
        }
        item {
            FavoriteSection(
                modifier = Modifier.padding(16.dp),
                data = state.favoriteList,
                onClickManage = {
                    event(DashboardScreenEvent.OnClickManage)
                },
                onClickRepeat = {
                    event(DashboardScreenEvent.OnClickRepeat)
                },
            )
        }
    }
}

@Preview
@Composable
private fun DashboardScreenTopBarPreview() {
    DashboardScreenTopBar(
        userAvatar = Res.drawable.img_man_side_view,
        userName = "Alex",
        onClickNotification = {},
    )
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(
        state = DashboardScreenState(
            userAvatar = Res.drawable.img_man_side_view,
            userName = "Alex",
            totalBalance = "$12,345.67",
            accountNumber = "...1234",
            menuList = listOf(
                MenuCardAttribute(
                    iconRes = Res.drawable.ic_transfer,
                    label = "Transfer",
                ),
                MenuCardAttribute(
                    iconRes = Res.drawable.ic_wallet,
                    label = "Deposit",
                ),
                MenuCardAttribute(
                    iconRes = Res.drawable.ic_receipt,
                    label = "Pay Bills",
                ),
                MenuCardAttribute(
                    iconRes = Res.drawable.ic_menu_outline,
                    label = "More",
                ),
            ),
            paymentList = listOf(
                BillSectionResourceItemAttribute(
                    iconRes = Res.drawable.ic_netflix_square,
                    label = "Netflix",
                    description = "Due: Oct 28",
                    price = "$15.49",
                    iconBackgroundColor = Color.Magenta,
                ),
                BillSectionResourceItemAttribute(
                    iconRes = Res.drawable.ic_light_bulb,
                    label = "City Power",
                    description = "Due: Nov 02",
                    price = "$78.20",
                    iconBackgroundColor = Color.Yellow,
                ),
            ),
            favoriteList = listOf(
                FavoriteSectionResourceItemAttribute(
                    imageRes = Res.drawable.img_mom_portrait,
                    label = "Mom",
                    description = "Last transfer: $50.00",
                ),
                FavoriteSectionResourceItemAttribute(
                    imageRes = Res.drawable.img_landlord_portrait,
                    label = "Landlord",
                    description = "Last transfer: $1200.00",
                ),
            ),
        ),
    )
}
