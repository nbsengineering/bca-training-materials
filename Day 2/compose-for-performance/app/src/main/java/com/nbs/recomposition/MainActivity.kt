package com.nbs.recomposition

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nbs.recomposition.ui.theme.RecompositionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT, Color.TRANSPARENT
            ),
        )
        setContent {
            RecompositionTheme {
                AppNav()
            }
        }
    }
}

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("first") { FirstTaskScreen() }
        composable("double") { SecondTaskScreen() }
        composable("third") { ThirdTaskScreen() }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(innerPadding)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("first") }) {
                Text(
                    fontSize = 12.sp,
                    text = "First Task : Recomposition + LayoutInspector"
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("double") }) {
                Text(
                    fontSize = 12.sp,
                    text = "Second Task : @Stable and @Immutable"
                )
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { navController.navigate("third") }) {
                Text(
                    fontSize = 12.sp,
                    text = "Third Task : Lazy Layout + Key"
                )
            }
        }
    }
}