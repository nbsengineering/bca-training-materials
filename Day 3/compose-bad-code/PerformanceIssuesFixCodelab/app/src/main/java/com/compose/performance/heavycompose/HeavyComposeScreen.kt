@file:OptIn(ExperimentalMaterial3Api::class)

package com.compose.performance.heavycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * The main screen that hosts the intentionally complex content and measures render time.
 * @param startTimeMs The time recorded when the Activity's onCreate method started.
 */
@Composable
fun HeavyComposeScreen(startTimeMs: Long) {
    // State to hold the calculated render time string for display
    val estimatedTime = remember { mutableStateOf("Calculating initial render time...") }

    // 3. LaunchedEffect runs *after* the initial composition and layout pass finishes.
    // This marks the end time for our client-side measurement.
    LaunchedEffect(Unit) {
        val endTimeMs = System.currentTimeMillis()
        val durationMs = endTimeMs - startTimeMs
        estimatedTime.value = "Estimated Initial Render Time: $durationMs ms (Time from onCreate to post-composition)"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Slow Render Heavy Compose", color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        containerColor = Color(0xFFF0F4F8)
    ) { paddingValues ->
        // The content is wrapped in a scrollable column to ensure all items are composed and laid out
        // synchronously on initial launch, maximizing the startup performance hit.
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 4. Display the calculated time in a noticeable box
            Text(
                text = estimatedTime.value,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .background(Color.Yellow.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            )

            // Call the composable that generates deep, synchronous complexity
            HeavyLoadContent()
        }
    }
}

/**
 * Generates the core performance bottleneck.
 * It now instantiates 200 items, each nested 20 layers deep.
 */
@Composable
fun HeavyLoadContent() {
    // Generate a list of 200 items to iterate over
    val numberOfItems = 300
    repeat(numberOfItems) { index ->
        // The key to slowness: Each item is deeply nested (depth 20)
        DeeplyNestedListItem(
            itemIndex = index + 1,
            // Increasing the depth significantly slows down the initial render
            currentDepth = 30
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

/**
 * A recursive composable function that creates a deep layout tree.
 * This structure is extremely expensive for the JIT compiler to optimize on-the-fly,
 * resulting in poor initial performance.
 *
 * @param itemIndex The index of the item in the main list.
 * @param currentDepth The remaining depth of recursion.
 */
@Composable
fun DeeplyNestedListItem(itemIndex: Int, currentDepth: Int) {
    val color = when (currentDepth % 4) {
        0 -> Color(0xFFADD8E6) // Light Blue
        1 -> Color(0xFFB0C4DE) // Light Steel Blue
        2 -> Color(0xFFE0FFFF) // Light Cyan
        else -> Color(0xFFF0F8FF) // Alice Blue
    }

    // Apply complexity through different layout types and modifiers at each level
    Box(
        modifier = Modifier
            .fillMaxWidth(if (currentDepth == 10) 1f else 0.95f) // Keeps the cascading look
            .background(color, RoundedCornerShape(4.dp))
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (currentDepth == 20) {
                // Top-level item detail
                Text(
                    text = "Item $itemIndex: Depth $currentDepth",
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(2.dp)
                )
            } else if (currentDepth > 1) {
                // Recursive step: create another nested layer
                DeeplyNestedListItem(itemIndex, currentDepth - 1)
            } else {
                // Base case (Depth 1)
                Text(
                    text = "Inner Leaf",
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(start = 4.dp, top = 1.dp, bottom = 1.dp)
                )
            }
        }
    }
}