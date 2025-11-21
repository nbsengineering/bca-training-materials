package com.jetbrains.kmpapp.ui.atoms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Avatar(imageRes: DrawableResource, modifier: Modifier = Modifier, size: Dp = 48.dp) {
    Image(
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        painter = painterResource(imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Preview
@Composable
fun AvatarPreview() {
    Avatar(imageRes = Res.drawable.img_man_side_view)
}
