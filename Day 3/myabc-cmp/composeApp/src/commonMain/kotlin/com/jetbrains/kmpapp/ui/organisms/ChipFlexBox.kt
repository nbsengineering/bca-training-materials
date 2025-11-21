package com.jetbrains.kmpapp.ui.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.ui.molecules.Chip
import com.jetbrains.kmpapp.ui.molecules.ChipAttribute
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipFlexbox(
    items: List<ChipAttribute>,
    modifier: Modifier = Modifier,
    maxItemsInRow: Int = Int.MAX_VALUE,
    horizontalGap: Dp = 8.dp,
    verticalGap: Dp = 8.dp,
    onChipClick: (ChipAttribute) -> Unit = {},
) {
    FlowRow(
        modifier = modifier,
        maxItemsInEachRow = maxItemsInRow,
        horizontalArrangement = Arrangement.spacedBy(horizontalGap),
        verticalArrangement = Arrangement.spacedBy(verticalGap),
    ) {
        items.forEach { attr ->
            Chip(
                attribute = attr,
                onClick = { onChipClick(attr) },
            )
        }
    }
}

@Preview()
@Composable
private fun ChipFlexboxPreview() {
    val chips = listOf(
        ChipAttribute(
            leadingIconRes = Res.drawable.ic_date_picker,
            label = "Last 30 days",
            trailingIconRes = Res.drawable.ic_chevron_down,
        ),
        ChipAttribute(label = "All", isSelected = true),
        ChipAttribute(label = "Income"),
        ChipAttribute(label = "Expenses"),
    )

    ChipFlexbox(
        items = chips,
        onChipClick = { clicked ->
            println("Clicked: $clicked")
        },
    )
}