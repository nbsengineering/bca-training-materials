package com.nbs.composemigration.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.asImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.android.tools.screenshot.PreviewTest
import com.nbs.composemigration.R
import com.nbs.composemigration.model.detailedBios
import com.nbs.composemigration.model.topScorers
import com.nbs.composemigration.ui.theme.AppTheme

@OptIn(ExperimentalCoilApi::class)
@PreviewTest
@Preview
@Composable
fun PlayerCard1() {
    val context = LocalContext.current
    val pvHandler = AsyncImagePreviewHandler {
        val data = it.data
        if (data is String) {
            if (data.startsWith("https://resources.premierleague.com/premierleague25/photos/players")) {
                return@AsyncImagePreviewHandler context.resources.getDrawable(R.drawable.img_sample_player)
                    .asImage()
            }
            if (data.startsWith("https://resources.premierleague.com/premierleague25/badges-alt")) {
                return@AsyncImagePreviewHandler context.resources.getDrawable(R.drawable.img_sample_club)
                    .asImage()
            }
        }
        return@AsyncImagePreviewHandler ColorImage(Color.Red.toArgb())
    }

    CompositionLocalProvider(LocalAsyncImagePreviewHandler provides pvHandler) {
        AppTheme {
            PlayerCard(
                topScorers.last(),
                detailedBios.last(),
            )
        }
    }
}