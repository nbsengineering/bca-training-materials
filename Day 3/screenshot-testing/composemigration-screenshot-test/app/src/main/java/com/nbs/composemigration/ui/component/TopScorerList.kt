package com.nbs.composemigration.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nbs.composemigration.model.TopScorer

@Composable
fun TopScorersList(
    scorers: List<TopScorer>,
    navigationBarPadding: PaddingValues,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = navigationBarPadding.calculateBottomPadding()),
    ) {
        itemsIndexed(scorers) { index, scorer ->
            val rank = index + 1

            TopScorerItem(
                scorer = scorer,
                rank = rank,
                onClick = onClick,
            )
        }
    }
}