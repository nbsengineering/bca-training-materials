package com.nbs.composemigration

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nbs.composemigration.model.TopScorer
import com.nbs.composemigration.model.topScorers
import com.nbs.composemigration.ui.component.TopScorersList
import com.nbs.composemigration.ui.detail.DetailActivity
import com.nbs.composemigration.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        setContent {
            AppTheme {
                TopScorerScreen(
                    onScorerClick = { scorerId ->
                        startActivity(
                            Intent(this, DetailActivity::class.java)
                                .putExtra(DetailActivity.EXTRA_SCORER_ID, scorerId)
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun TopScorerScreen(
    onScorerClick: (Int) -> Unit
) {
    var scorers by remember { mutableStateOf(emptyList<TopScorer>()) }

    LaunchedEffect(Unit) {
        scorers = topScorers
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        val navigationBarPadding = WindowInsets.navigationBars.asPaddingValues()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            Text(
                text = "Top Scorer",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium,
            )

            TopScorersList(
                scorers = scorers,
                navigationBarPadding = navigationBarPadding,
                onClick = onScorerClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            )
        }
    }
}