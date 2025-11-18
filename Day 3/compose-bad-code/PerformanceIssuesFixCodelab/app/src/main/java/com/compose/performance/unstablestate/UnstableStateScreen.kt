package com.compose.performance.unstablestate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
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

@Composable
fun UnstableClassScreen() {
    // State A: Simple counter to prove that *other* state changes *do* cause recomposition.
    var triggerCounter by remember { mutableStateOf(0) }

    // State B: CRITICAL MISTAKE: Storing a standard MutableList inside mutableStateOf.
    // Compose only tracks changes to the 'list' reference itself, not its internal contents.
    val tasksState =
        remember { mutableStateOf(mutableListOf("Task 1: Buy Milk", "Task 2: Walk Dog")) }
    val tasksList = tasksState.value // Get the actual MutableList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    // This updates the list in place, but DOES NOT change
                    // the 'tasksState' reference.
                    tasksList.add("Task ${tasksList.size + 1}: NEW TASK (Hidden)")
                    println("Added new task. List size: ${tasksList.size}")
                    // UI WILL NOT UPDATE, because Compose is unaware of the list mutation.
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task", tint = Color.White)
                Spacer(Modifier.width(8.dp))
                Text("Add Task", color = Color.White)
            }

            Button(
                onClick = {
                    // This changes a DIFFERENT state variable, forcing the whole screen to recompose.
                    // This is the only way the list update will be seen!
                    triggerCounter++
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFADADAD))
            ) {
                Text("Force Recompose")
            }
        }

        Text(
            text = "Recomposition Counter: $triggerCounter",
            modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Task List Display
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White, MaterialTheme.shapes.small)
                .padding(8.dp)
        ) {
            items(tasksList) { task ->
                ListItem(
                    headlineContent = { Text(task) },
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .background(Color(0xFFF0F0F0))
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "To see the new tasks, you must press 'Force Recompose'!",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFC62828)
        )
    }
}
