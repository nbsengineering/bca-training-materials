package com.nbs.composecustomization.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.composecustomization.R
import com.nbs.composecustomization.ui.molecules.MenuCard
import com.nbs.composecustomization.ui.molecules.MenuCardAttribute


data class MenuGridAttribute(
    val itemList: List<MenuCardAttribute>,
)

@Composable
fun MenuGrid(attribute: MenuGridAttribute) {
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
                    MenuCard(attribute = item)
                }

                repeat(4 - rowItems.size) {
                    Spacer(modifier = Modifier.size(0.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuGridPreview() {
    MenuGrid(
        attribute = MenuGridAttribute(
            itemList = listOf(
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
        ),
    )
}
