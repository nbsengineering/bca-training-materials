package com.nbs.recomposition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nbs.recomposition.component.ItemNewsContent

@Composable
fun SecondTaskScreen() {
    var counter by remember { mutableStateOf(0) }
    var newsData by remember {
        mutableStateOf(
            NewsData(
                xid = "dasd",
                title = "Title ",
                datePublish = "Date ",
                slug = "Slug",
                tags = mutableListOf("Tag 1", "Tag 2")
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ItemNewsContent(
            data = newsData
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                counter++
            }) {
            Text("Trigger Recomposition ${counter}")
        }
    }
}

data class NewsData(
    val xid: String,
    val title: String = "",
    val datePublish: String = "",
    val slug: String = "",
    val thumbnail: Int = R.drawable._50_400x250,
    val tags: MutableList<String> = mutableListOf()
)