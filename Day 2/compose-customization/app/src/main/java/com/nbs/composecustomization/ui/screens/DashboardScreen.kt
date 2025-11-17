package com.nbs.composecustomization.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.composecustomization.R
import com.nbs.composecustomization.ui.atoms.Avatar
import com.nbs.composecustomization.ui.atoms.SurfaceIcon
import com.nbs.composecustomization.ui.molecules.MenuCardAttribute
import com.nbs.composecustomization.ui.molecules.ResourceItem
import com.nbs.composecustomization.ui.molecules.ResourceItemAttribute
import com.nbs.composecustomization.ui.molecules.SummaryCard
import com.nbs.composecustomization.ui.organisms.BottomNavBar
import com.nbs.composecustomization.ui.organisms.MenuGrid
import com.nbs.composecustomization.ui.organisms.MenuGridAttribute
import com.nbs.composecustomization.ui.theme.colorOnPrimaryContainer
import com.nbs.composecustomization.ui.theme.colorPrimary
import com.nbs.composecustomization.ui.theme.colorPrimaryContainer
import com.nbs.composecustomization.ui.theme.colorSecondaryContainer


private data class DashboardBillResourceItemAttribute(
    @DrawableRes val iconRes: Int,
    val iconBackgroundColor: Color,
    val label: String,
    val description: String,
    val price: String,
)

@Composable
private fun DashboardBillSection(
    modifier: Modifier = Modifier,
    data: List<DashboardBillResourceItemAttribute>,
    onClickViewAll: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Upcoming Bills", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.clickable(onClick = onClickViewAll),
                text = "View All",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorPrimary,
            )
        }
        data.forEach { item ->
            Card(
                colors = CardDefaults.cardColors(colorSecondaryContainer),
            ) {
                ResourceItem(
                    modifier = Modifier.padding(16.dp),
                    attribute = ResourceItemAttribute(
                        label = item.label,
                        description = item.description,
                    ),
                    leadingContent = {
                        SurfaceIcon(
                            iconRes = R.drawable.ic_card,
                            size = 28.dp,
                            iconPadding = 8.dp,
                            iconColor = colorOnPrimaryContainer,
                            backgroundColor = item.iconBackgroundColor,
                        )
                    },
                    trailingContent = {
                        Text(
                            text = item.price,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                )
            }
        }
    }
}

private data class DashboardFavoriteResourceItemAttribute(
    @DrawableRes val imageRes: Int,
    val label: String,
    val description: String,
)

@Composable
private fun DashboardFavoriteSection(
    modifier: Modifier = Modifier,
    data: List<DashboardFavoriteResourceItemAttribute>,
    onClickManage: () -> Unit,
    onClickRepeat: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Favorites", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(
                modifier = Modifier.clickable(onClick = onClickManage),
                text = "Manage",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colorPrimary,
            )
        }
        data.forEach { item ->
            Card(
                colors = CardDefaults.cardColors(colorSecondaryContainer),
            ) {
                ResourceItem(
                    modifier = Modifier.padding(16.dp),
                    attribute = ResourceItemAttribute(
                        label = item.label,
                        description = item.description,
                    ),
                    leadingContent = {
                        Avatar(
                            imageRes = item.imageRes,
                            size = 40.dp,
                        )
                    },
                    trailingContent = {
                        Text(
                            modifier = Modifier
                                .clickable(onClick = onClickRepeat)
                                .clip(RoundedCornerShape(24.dp))
                                .background(colorPrimaryContainer)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "Repeat",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = colorOnPrimaryContainer,
                        )
                    },
                )
            }
        }
    }
}

private data class DashboardScreenState(
    @DrawableRes val userAvatar: Int = 0,
    val userName: String = "",
    val totalBalance: String = "",
    val accountNumber: String = "",
    val menuList: List<MenuCardAttribute> = emptyList(),
    val paymentList: List<DashboardBillResourceItemAttribute> = emptyList(),
    val favoriteList: List<DashboardFavoriteResourceItemAttribute> = emptyList(),
)

private sealed class DashboardScreenEvent {
    data object OnClickNotification : DashboardScreenEvent()
    data object OnClickMenu : DashboardScreenEvent()
    data object OnClickViewAll : DashboardScreenEvent()
    data object OnClickManage : DashboardScreenEvent()
    data object OnClickRepeat : DashboardScreenEvent()
    data object OnClickBottomNav : DashboardScreenEvent()
}

@Composable
private fun DashboardScreen(
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
        bottomBar = {
            BottomNavBar(
                onClick = {
                    event(DashboardScreenEvent.OnClickBottomNav)
                },
            )
        },
    )
}

@Composable
private fun DashboardScreenTopBar(
    @DrawableRes userAvatar: Int,
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
            painter = painterResource(R.drawable.ic_notification_fill),
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
                attribute = MenuGridAttribute(
                    itemList = state.menuList,
                ),
            )
        }
        item {
            DashboardBillSection(
                modifier = Modifier.padding(16.dp),
                data = state.paymentList,
                onClickViewAll = {
                    event(DashboardScreenEvent.OnClickViewAll)
                },
            )
        }
        item {
            DashboardFavoriteSection(
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
        userAvatar = R.drawable.img_man_side_view,
        userName = "Alex",
        onClickNotification = {},
    )
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(
        state = DashboardScreenState(
            userAvatar = R.drawable.img_man_side_view,
            userName = "Alex",
            totalBalance = "$12,345.67",
            accountNumber = "...1234",
            menuList = listOf(
                MenuCardAttribute(
                    iconRes = R.drawable.ic_transfer,
                    label = "Transfer",
                ),
                MenuCardAttribute(
                    iconRes = R.drawable.ic_wallet,
                    label = "Deposit",
                ),
                MenuCardAttribute(
                    iconRes = R.drawable.ic_receipt,
                    label = "Pay Bills",
                ),
                MenuCardAttribute(
                    iconRes = R.drawable.ic_menu_outline,
                    label = "More",
                ),
            ),
            paymentList = listOf(
                DashboardBillResourceItemAttribute(
                    iconRes = R.drawable.ic_netflix_square,
                    label = "Netflix",
                    description = "Due: Oct 28",
                    price = "$15.49",
                    iconBackgroundColor = Color.Magenta,
                ),
                DashboardBillResourceItemAttribute(
                    iconRes = R.drawable.ic_light_bulb,
                    label = "City Power",
                    description = "Due: Nov 02",
                    price = "$78.20",
                    iconBackgroundColor = Color.Yellow,
                ),
            ),
            favoriteList = listOf(
                DashboardFavoriteResourceItemAttribute(
                    imageRes = R.drawable.img_mom_portrait,
                    label = "Mom",
                    description = "Last transfer: $50.00",
                ),
                DashboardFavoriteResourceItemAttribute(
                    imageRes = R.drawable.img_landlord_portrait,
                    label = "Landlord",
                    description = "Last transfer: $1200.00",
                ),
            ),
        ),
    )
}

@Preview
@Composable
private fun DashboardBillSectionPreview() {
    DashboardBillSection(
        onClickViewAll = {},
        data = listOf(
            DashboardBillResourceItemAttribute(
                iconRes = R.drawable.ic_netflix_square,
                label = "Netflix",
                description = "Due: Oct 28",
                price = "$15.49",
                iconBackgroundColor = Color.Magenta,
            ),
            DashboardBillResourceItemAttribute(
                iconRes = R.drawable.ic_light_bulb,
                label = "City Power",
                description = "Due: Nov 02",
                price = "$78.20",
                iconBackgroundColor = Color.Yellow,
            ),
        ),
    )
}

@Preview
@Composable
private fun DashboardFavoriteSectionPreview() {
    DashboardFavoriteSection(
        onClickManage = {},
        onClickRepeat = {},
        data = listOf(
            DashboardFavoriteResourceItemAttribute(
                imageRes = R.drawable.img_mom_portrait,
                label = "Mom",
                description = "Last transfer: $50.00",
            ),
            DashboardFavoriteResourceItemAttribute(
                imageRes = R.drawable.img_landlord_portrait,
                label = "Landlord",
                description = "Last transfer: $1200.00",
            ),
        ),
    )
}
