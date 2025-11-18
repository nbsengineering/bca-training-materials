package com.compose.performance.blockingcalculation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking

fun blockingCalculation(iterations: Long = 1_000_000_000L): Long {
    var result = 0L
    val startTime = System.currentTimeMillis()

    // WARNING: This is the part that will block the Main Thread if called directly from a Composable click handler!
    for (i in 0 until iterations) {
        // Simple but repeated arithmetic operation
        result += (i % 10)
    }
    val endTime = System.currentTimeMillis()
    println("Problematic Calculation finished in: ${endTime - startTime} ms")
    return result
}

@Composable
fun BlockingCalculationScreen() {
    // State to track if calculation is running
    var isCalculating by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                // CRITICAL MISTAKE: Calling blockingCalculation() directly on the main thread.
                runBlocking {
                    isCalculating = true
                    blockingCalculation()
                    isCalculating = false
                }
            },
            enabled = !isCalculating,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828)) // Red button
        ) {
            Text(if (isCalculating) "FREEZING UI..." else "Start Expensive Calculation")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Use this constantly moving element to visually check for jank/stuttering
        Text(if (isCalculating) "Main thread blocked" else "Main thread safe", modifier = Modifier.padding(16.dp))
        Spacer(Modifier.height(16.dp))
        BouncingDot()
    }
}

// A simple animation to visualize the jank
@Composable
fun BouncingDot() {
    val infiniteTransition = rememberInfiniteTransition(label = "BouncingDotTransition")
    val yOffset by infiniteTransition.animateFloat(
        initialValue = -50f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "YOffset"
    )

    // If the calculation blocks the main thread, this dot will visibly stop moving.
    Box(
        modifier = Modifier
            .padding(top = 32.dp)
            .size(20.dp)
            .offset(y = yOffset.dp)
            .background(
                color = Color(0xFF388E3C), // Green when moving
                shape = androidx.compose.foundation.shape.CircleShape
            )
    )
}