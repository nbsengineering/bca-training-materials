package com.nbs.composemigration.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.nbs.composemigration.model.TopScorer
import com.nbs.composemigration.model.topScorers
import com.nbs.composemigration.ui.theme.AppTheme

@Composable
fun TopScorerItem(
    scorer: TopScorer,
    rank: Int,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(scorer.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rank.toString(),
                modifier = Modifier.padding(end = 16.dp),
                style = MaterialTheme.typography.bodyLarge,
            )

            AsyncImage(
                model = scorer.imageUrl,
                contentDescription = "Player Photo",
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp),
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
            ) {
                Text(
                    text = scorer.name,
                    style = MaterialTheme.typography.bodyLarge,
                )

                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = scorer.club.logoUrl,
                        contentDescription = "Club Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 8.dp),
                    )

                    Text(
                        text = scorer.club.name,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }

            Text(
                text = scorer.goal.toString(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Preview
@Composable
fun TopScorerItemPreview() {
    AppTheme {
        TopScorerItem(
            scorer = topScorers.first(),
            rank = 1,
        ) { }
    }
}