package com.jetbrains.kmpapp.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetbrains.kmpapp.ui.atoms.Avatar
import com.jetbrains.kmpapp.ui.theme.colorOnTertiaryContainer
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.ui.tooling.preview.Preview

data class ProfileComponentAttribute(
    val avatarImageRes: DrawableResource,
    val userName: String,
    val userEmail: String,
)

@Composable
fun ProfileComponent(modifier: Modifier = Modifier, attribute: ProfileComponentAttribute) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Avatar(imageRes = attribute.avatarImageRes, size = 72.dp)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = attribute.userName, fontWeight = FontWeight.Bold, fontSize = 28.sp)
            Text(text = attribute.userEmail, fontSize = 18.sp, color = colorOnTertiaryContainer)
        }
    }
}

@Preview
@Composable
private fun ProfileComponentPreview() {
    ProfileComponent(
        attribute = ProfileComponentAttribute(
            avatarImageRes = Res.drawable.img_man_side_view,
            userName = "Alex Doe",
            userEmail = "alex.doe@email.com",
        ),
    )
}
