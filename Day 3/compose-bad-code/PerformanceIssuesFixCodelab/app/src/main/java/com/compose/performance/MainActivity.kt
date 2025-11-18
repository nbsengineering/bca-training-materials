/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.compose.performance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.performance.blockingcalculation.BlockingCalculationScreen
import com.compose.performance.heavycompose.HeavyComposeScreen
import com.compose.performance.infiniteloop.InfiniteLoopScreen
import com.compose.performance.ui.theme.PerformanceWorkshopTheme
import com.compose.performance.unstablestate.UnstableClassScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: PerformanceCodeLabViewModel = viewModel {
                val startFromStep = intent.getStringExtra(EXTRA_START_TASK)
                PerformanceCodeLabViewModel(startFromStep)
            }

            PerformanceWorkshopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { testTagsAsResourceId = true },
                    color = MaterialTheme.colorScheme.background
                ) {
                    PerformanceCodeLabScreen(
                        selectedPage = viewModel.selectedPage.value,
                        onPageSelected = {
                            viewModel.selectedPage.value = it
                        }
                    )
                }
            }
        }
    }
}

private class PerformanceCodeLabViewModel(startFromStep: String?) : ViewModel() {
    val selectedPage = mutableStateOf(TaskScreen.from(startFromStep))
}

@Composable
private fun PerformanceCodeLabScreen(
    selectedPage: TaskScreen,
    onPageSelected: (selected: TaskScreen) -> Unit
) {
    BackHandler(enabled = !selectedPage.isFirst) {
        onPageSelected(selectedPage.previous())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .testTag(selectedPage.id),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val previousTaskLabel = stringResource(R.string.previous_task)
            val nextTaskLabel = stringResource(R.string.next_task)

            IconButton(
                onClick = { onPageSelected(selectedPage.previous()) },
                modifier = Modifier.semantics {
                    contentDescription = previousTaskLabel
                }
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.ArrowBack),
                    contentDescription = null
                )
            }

            Text("Task ${selectedPage.ordinal + 1} / ${TaskScreen.entries.lastIndex + 1}")

            IconButton(
                onClick = { onPageSelected(selectedPage.next()) },
                modifier = Modifier.semantics {
                    contentDescription = nextTaskLabel }
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.ArrowForward),
                    contentDescription = null
                )
            }
        }
        Text(text = selectedPage.label)
        HorizontalDivider()

        selectedPage.composable()
    }
}

const val EXTRA_START_TASK = "EXTRA_START_TASK"

private enum class TaskScreen(
    val id: String,
    val label: String,
    val composable: @Composable () -> Unit
) {
    InfiniteLoop(
        id = "infinite_loop",
        label = "Recomposition - Infinite Loop",
        composable = { InfiniteLoopScreen() }
    ),
    BlockingCalculation(
        id = "blocking_calculation",
        label = "Calculation - Blocking Main Thread",
        composable = { BlockingCalculationScreen() }
    ),
    UnstableClass(
        id = "unstable_class",
        label = "Unstable - State",
        composable = {
            UnstableClassScreen()
        }
    ),
    HeavyComplexCompose(
        id = "heavy_complex_compose",
        label = "Heavy - Composable",
        composable = { HeavyComposeScreen(System.currentTimeMillis()) }
    );
    val isFirst get() = ordinal == 0

    fun previous() = entries[Math.floorMod(ordinal - 1, entries.size)]
    fun next() = entries[Math.floorMod(ordinal + 1, entries.size)]

    companion object {
        fun from(extra: String?) = entries.firstOrNull { it.id == extra } ?: entries.first()
    }
}
