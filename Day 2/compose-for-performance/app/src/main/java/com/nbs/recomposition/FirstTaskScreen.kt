package com.nbs.recomposition

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nbs.recomposition.component.ItemHorizontalContent
import com.nbs.recomposition.component.PromoData

@Composable
fun FirstTaskScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            var counter by remember { mutableStateOf(0) }
            var isStartInfiniteCounter by remember { mutableStateOf(false) }



            Text(
                modifier = Modifier.padding(16.dp),
                text = "First Task",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp),
                text = "This page is Playground for recomposition, in here you can learn how to use and gather information about recomposition from LayoutInspector\n" +
                        "1. Open Layout Inspector : Tools > Layout Inspector\n" +
                        "2. Make sure Toggle Layout Inspector is Active\n" +
                        "3. On Screen when View turn blue , recomposition trigger there.\n" +
                        "4. On Recomposition counts if recomposition triggered the counter will be increase",
                style = MaterialTheme.typography.bodyMedium
            )
            Row {
                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        counter++
                    }) {
                    Text("Add Counter")
                }

                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = {
                        counter = 1
                    }) {
                    Text("Update to 1")
                }
            }
            Counter(counter)
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    isStartInfiniteCounter = !isStartInfiniteCounter
                }) {
                Text("${if (isStartInfiniteCounter) "Stop" else "Start"} Infinite Counter")
            }
            InfiniteCounter(isStartInfiniteCounter)

        }
    }


}

@Composable
fun InfiniteCounter(isStartInfiniteCounter: Boolean) {
    var counter by remember { mutableStateOf(0) }

    SideEffect {
        Log.d("Recomposition", "Recomposition : infinite counter $isStartInfiniteCounter : $counter ")
    }

    if (isStartInfiniteCounter) {
        LaunchedEffect(counter) {
            // on kotlin 2, it have strong skippable that make counter++ will only trigger once outside Launched Effect
            counter++
        }
    }

    Text(
        text = "Infinite Counter: $counter"
    )
}

@Composable
fun Counter(counter: Int) {

    SideEffect {
        Log.d("Recomposition", "Recomposition : skippable counter $counter")
    }

    Text(text = "Recomposition Counter: $counter")
}