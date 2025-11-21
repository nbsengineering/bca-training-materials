package com.jetbrains.kmpapp.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.ui.theme.colorOnSecondaryContainer
import com.jetbrains.kmpapp.ui.theme.colorOnTertiary
import com.jetbrains.kmpapp.ui.theme.colorPrimaryContainer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import myabc_cmp.composeapp.generated.resources.*

data class BottomNavBarItem(
    val route: String,
    val label: String,
    val iconRes: DrawableResource,
)

@Composable
private fun BottomNavMenu(
    iconRes: DrawableResource,
    title: String,
    isActive: Boolean = false,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (isActive) colorPrimaryContainer else Color.White)
                .padding(horizontal = 12.dp, vertical = 2.dp)
                .size(24.dp),
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = if (isActive) colorOnTertiary else colorOnSecondaryContainer,
        )
        Text(
            text = title,
            fontSize = 14.sp,
            color = if (isActive) colorOnTertiary else colorOnSecondaryContainer,
        )
    }
}

@Composable
fun BottomNavBar(
    items: List<BottomNavBarItem>,
    selectedRoute: String?,
    onItemSelected: (BottomNavBarItem) -> Unit,
) {
    Column {
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            items.forEach { item ->
                BottomNavMenu(
                    title = item.label,
                    iconRes = item.iconRes,
                    isActive = item.route == selectedRoute,
                    onClick = { onItemSelected(item) },
                )
            }
        }
    }
}

@Preview()
@Composable
private fun BottomNavMenuPreview() {
    BottomNavMenu(
        title = "Profile",
        iconRes = Res.drawable.ic_transfer,
        isActive = false,
        onClick = {},
    )
}

@Preview
@Composable
private fun BottomNavBarPreview() {
    BottomNavBar(
        items = listOf(
            BottomNavBarItem(
                route = "home",
                label = "Home",
                iconRes = Res.drawable.ic_home_outline,
            ),
            BottomNavBarItem(
                route = "notifications",
                label = "Notif",
                iconRes = Res.drawable.ic_notification_fill,
            ),
            BottomNavBarItem(
                route = "settings",
                label = "Settings",
                iconRes = Res.drawable.ic_setting,
            ),
        ),
        selectedRoute = "home",
        onItemSelected = {},
    )
}
