@file:OptIn(ExperimentalMaterial3Api::class)

package com.compose.performance.infiniteloop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define a complex data class to hold the state
data class LoopState(val count: Int)

@Composable
fun InfiniteLoopScreen() {
    val scrollState = rememberScrollState()

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Define mutable state using the complex data class
            var loopState by remember { mutableStateOf(LoopState(0)) }

            // CHANGE 2: Pass the unstable 'loopState' object (which changes every time)
            // as the final parameter (type Any).
            RecompositionCountCard(
                count = loopState.count,
                onRecompose = { loopState = loopState.copy(count = loopState.count + 1) },
            )
        }
    }
}

@Composable
fun RecompositionCountCard(count: Int, onRecompose: () -> Unit) {

    // 2. !!! CRITICAL BUG LINE - The Infinite Loop Trigger !!!
    SideEffect {
        // This function will continuously increment the state,
        // causing infinite recompositions.
        onRecompose.invoke()
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            // The Text is static to keep the UI simple for profiling.
            Text(
                "(Loop is active) ${" ".repeat(count)} ",
                color = Color(0xFFC62828),
                fontWeight = FontWeight.Black,
                maxLines = 1,
                overflow = TextOverflow.Visible,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Use the Android Profiler's CPU and Composition trace to confirm CPU usage.",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}