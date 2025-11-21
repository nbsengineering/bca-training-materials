package com.jetbrains.kmpapp.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.ui.molecules.MenuCard
import com.jetbrains.kmpapp.ui.molecules.MenuCardAttribute
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.ui.tooling.preview.Preview

data class MenuGridAttribute(
    val itemList: List<MenuCardAttribute>,
)

@Composable
fun MenuGrid(attribute: MenuGridAttribute, onClickMenu: (() -> Unit)? = null) {
    val items = attribute.itemList
    val chunkedItems = items.chunked(4)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                rowItems.forEach { item ->
                    MenuCard(attribute = item, onClick = onClickMenu)
                }

                repeat(4 - rowItems.size) {
                    Spacer(modifier = Modifier.size(0.dp))
                }
            }
        }
    }
}

@Preview()
@Composable
private fun MenuGridPreview() {
    MenuGrid(
        attribute = MenuGridAttribute(
            itemList = listOf(
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
        ),
    )
}
