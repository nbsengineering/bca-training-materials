package com.nbs.recomposition

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbs.recomposition.component.ItemHorizontalContent
import com.nbs.recomposition.component.promoList

@Composable
fun ThirdTaskScreen() {
    var type by remember { mutableStateOf("Column") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        ListTypeSelector(type) {
            type = it
        }
        when (type) {
            "Column" -> ColumnList()
            "Lazy" -> LazyColumnWithoutKeys()
            "Lazy With Key" -> LazyColumnWithKeys()
        }
    }
}

@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Search Promo") },
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun ColumnList() {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        SearchTextField(query = query, onQueryChange = { query = it })

        // Filtering
        val filtered = promoList.filter {
            it.title.contains(query, ignoreCase = true) || it.slug.contains(query, ignoreCase = true)
        }

        // ALL ITEMS RECOMPOSE EVERY TIME
        Column {
            filtered.forEach { data ->
                ItemHorizontalContent(
                    title = data.title,
                    promoData = data
                ) {

                }
            }
        }
    }
}

@Composable
fun LazyColumnWithoutKeys() {
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {


        SearchTextField(query = query, onQueryChange = { query = it })

        // Filtering
        val filtered = promoList.filter {
            it.title.contains(query, ignoreCase = true) || it.slug.contains(query, ignoreCase = true)
        }

        LazyColumn {
            items(filtered) { data ->
                ItemHorizontalContent(
                    title = data.title,
                    promoData = data
                ) {

                }
            }
        }
    }
}

@Composable
fun LazyColumnWithKeys() {
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {


        SearchTextField(query = query, onQueryChange = { query = it })

        // Filtering
        val filtered = promoList.filter {
            it.title.contains(query, ignoreCase = true) || it.slug.contains(query, ignoreCase = true)
        }

        LazyColumn {
            items(
                key = { it.xid },
                items = filtered
            ) { data ->
                ItemHorizontalContent(
                    title = data.title,
                    promoData = data
                ) {

                }
            }
        }
    }
}

@Composable
fun ListTypeSelector(
    selectedType: String,
    onTypeSelected: (String) -> Unit
) {
    val types = listOf("Column", "Lazy", "Lazy With Key")

    Row {
        types.forEach { type ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable { onTypeSelected(type) }
            ) {
                RadioButton(
                    selected = selectedType == type,
                    onClick = { onTypeSelected(type) }
                )
                Text(
                    text = type,
                    fontSize = 8.sp,
                )
            }
        }
    }
}
