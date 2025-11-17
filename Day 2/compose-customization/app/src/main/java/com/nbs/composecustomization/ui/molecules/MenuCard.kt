package com.nbs.composecustomization.ui.molecules

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.composecustomization.R
import com.nbs.composecustomization.ui.theme.colorOnPrimaryContainer
import com.nbs.composecustomization.ui.theme.colorPrimaryContainer

data class MenuCardAttribute(
    @DrawableRes val iconRes: Int,
    val label: String,
)

@Composable
fun MenuCard(attribute: MenuCardAttribute, onClick: (() -> Unit)? = null) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = colorPrimaryContainer),
            onClick = { onClick?.invoke() }
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(attribute.iconRes),
                    contentDescription = null,
                    tint = colorOnPrimaryContainer,
                )
            }
        }

        Text(text = attribute.label)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MenuCardPreview() {
    MenuCard(
        attribute = MenuCardAttribute(
            iconRes = R.drawable.ic_transfer,
            label = "Transfer",
        ),
    )
}