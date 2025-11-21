package com.jetbrains.kmpapp.ui.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.ui.theme.colorSecondaryContainer
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SurfaceIcon(
    iconRes: DrawableResource,
    modifier: Modifier = Modifier,
    size: Dp = 26.dp,
    iconColor: Color = Color.Black,
    backgroundColor: Color = colorSecondaryContainer,
    shape: Shape = CircleShape,
    iconPadding: Dp = 16.dp,
) {
    Icon(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .padding(iconPadding)
            .size(size),
        painter = painterResource(iconRes),
        contentDescription = null,
        tint = iconColor,
    )
}

@Preview
@Composable
private fun SurfaceIconPreview() {
    SurfaceIcon(iconRes = Res.drawable.ic_transfer)
}
