package com.jetbrains.kmpapp.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.ui.atoms.SurfaceIcon
import com.jetbrains.kmpapp.ui.molecules.ResourceItem
import com.jetbrains.kmpapp.ui.molecules.ResourceItemAttribute
import com.jetbrains.kmpapp.ui.theme.colorOnPrimary
import com.jetbrains.kmpapp.ui.theme.colorOnSecondaryContainer
import com.jetbrains.kmpapp.ui.theme.colorPrimary
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class MenuResourceItemAttribute(
    val iconRes: DrawableResource,
    val label: String,
    val description: String? = null,
)

data class SettingMenuSectionAttribute(
    val title: String,
    val itemList: List<MenuResourceItemAttribute> = emptyList(),
)

@Composable
fun SettingMenuSection(attribute: SettingMenuSectionAttribute) {
    Column(
        modifier = Modifier.background(Color.White),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = attribute.title,
            fontWeight = FontWeight.Medium,
            color = colorPrimary,
        )

        attribute.itemList.forEachIndexed { index, item ->
            ResourceItem(
                attribute = ResourceItemAttribute(
                    label = item.label,
                    description = item.description,
                ),
                leadingContent = {
                    SurfaceIcon(
                        iconRes = item.iconRes,
                        size = 28.dp,
                        iconPadding = 12.dp,
                        iconColor = colorOnSecondaryContainer,
                        backgroundColor = colorOnPrimary,
                    )
                },
                trailingContent = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_chevron_next),
                        contentDescription = null,
                        tint = Color.Gray,
                    )
                },
            )
        }

        HorizontalDivider(color = Color.LightGray)
    }
}

@Preview
@Composable
private fun SettingMenuSectionPreview() {
    SettingMenuSection(
        attribute = SettingMenuSectionAttribute(
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
    )
}