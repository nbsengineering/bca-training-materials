package com.nbs.composemigration.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil3.compose.AsyncImage
import com.nbs.composemigration.model.DetailedScorerBio
import com.nbs.composemigration.model.TopScorer
import com.nbs.composemigration.model.detailedBios
import com.nbs.composemigration.model.topScorers
import com.nbs.composemigration.ui.theme.AppTheme

@Composable
fun PlayerCard(
    scorer: TopScorer,
    bio: DetailedScorerBio,
) {
    val club = scorer.club

    val cardBackgroundColor = remember(club.primaryColorHex) {
        Color(club.primaryColorHex.toColorInt())
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            AsyncImage(
                model = scorer.imageUrl,
                contentDescription = "Player Photo",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .width(80.dp)
                    .height(100.dp),
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp),
            ) {

                Text(
                    text = scorer.name,
                    style = MaterialTheme.typography.titleMedium,
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = club.logoUrl,
                        contentDescription = "Club Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp),
                    )

                    Text(
                        text = club.name,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }

                Row(
                    modifier = Modifier.padding(top = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = bio.position,
                        style = MaterialTheme.typography.labelSmall,
                    )

                    Text(
                        text = "•",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .padding(end = 6.dp),
                    )

                    Text(
                        text = bio.shirtNumber.toString(),
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PlayerCardPreview() {
    AppTheme {
        PlayerCard(
            scorer = topScorers.first(),
            bio = detailedBios.first(),
        )
    }
}