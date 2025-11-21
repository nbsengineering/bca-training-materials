package com.jetbrains.kmpapp.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.ui.atoms.SurfaceIcon
import com.jetbrains.kmpapp.ui.theme.colorOnPrimaryContainer
import com.jetbrains.kmpapp.ui.theme.colorPrimaryContainer
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class MenuCardAttribute(
    val iconRes: DrawableResource,
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
            SurfaceIcon(
                size = 20.dp,
                iconRes = attribute.iconRes,
                backgroundColor = colorPrimaryContainer,
                iconColor = colorOnPrimaryContainer,
            )
        }

        Text(text = attribute.label)
    }
}

@Preview()
@Composable
private fun MenuCardPreview() {
    MenuCard(
        attribute = MenuCardAttribute(
            iconRes = Res.drawable.ic_transfer,
            label = "Transfer",
        ),
    )
}