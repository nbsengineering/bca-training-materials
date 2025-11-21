package com.jetbrains.kmpapp.ui.organisms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.ui.atoms.SurfaceIcon
import com.jetbrains.kmpapp.ui.molecules.ResourceItem
import com.jetbrains.kmpapp.ui.molecules.ResourceItemAttribute
import com.jetbrains.kmpapp.ui.theme.colorOnSecondaryContainer
import com.jetbrains.kmpapp.ui.theme.colorPrimary
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class TransactionResourceItemAttribute(
    val iconRes: DrawableResource,
    val label: String,
    val description: String,
    val price: String,
    val isCredit: Boolean = false,
)

data class TransactionHistorySectionAttribute(
    val date: String,
    val itemList: List<TransactionResourceItemAttribute> = emptyList(),
)

@Composable
fun TransactionHistorySection(attribute: TransactionHistorySectionAttribute) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = attribute.date,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = colorPrimary,
        )

        attribute.itemList.forEach { item ->
            ResourceItem(
                attribute = ResourceItemAttribute(
                    label = item.label,
                    description = item.description,
                ),
                leadingContent = {
                    SurfaceIcon(
                        iconRes = item.iconRes,
                        size = 26.dp,
                        iconPadding = 8.dp,
                        iconColor = colorOnSecondaryContainer,
                    )
                },
                trailingContent = {
                    val symbol = if (item.isCredit) "+" else "-"
                    Text(
                        text = symbol + item.price,
                        fontSize = 16.sp,
                        color = if (item.isCredit) Color.Green else Color.Red,
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun TransactionHistorySectionPreview() {
    TransactionHistorySection(
        attribute = TransactionHistorySectionAttribute(
            date = "Today",
            itemList = listOf(
                TransactionResourceItemAttribute(
                    iconRes = Res.drawable.ic_cart_outline,
                    label = "Amazon.com",
                    description = "Online Purchase",
                    price = "$78.50",
                ),
                TransactionResourceItemAttribute(
                    iconRes = Res.drawable.ic_cup_outline,
                    label = "Starbucks",
                    description = "Coffee",
                    price = "$5.75",
                ),
            ),
        ),
    )
}