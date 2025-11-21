package com.jetbrains.kmpapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.ui.molecules.AccountBalanceCard
import com.jetbrains.kmpapp.ui.molecules.ChipAttribute
import com.jetbrains.kmpapp.ui.organisms.ChipFlexbox
import com.jetbrains.kmpapp.ui.organisms.HeaderPage
import com.jetbrains.kmpapp.ui.organisms.TransactionHistorySection
import com.jetbrains.kmpapp.ui.organisms.TransactionHistorySectionAttribute
import com.jetbrains.kmpapp.ui.organisms.TransactionResourceItemAttribute
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.ui.tooling.preview.Preview

data class TransactionScreenState(
    val accountBalance: String = "",
    val accountNumber: String = "",
    val chipList: List<ChipAttribute> = emptyList(),
    val transactionList: List<TransactionHistorySectionAttribute> = emptyList(),
)

sealed class TransactionScreenEvent {
    data class OnClickChip(val item: ChipAttribute) : TransactionScreenEvent()
    data object OnClickBack : TransactionScreenEvent()
    data object OnClickSearch : TransactionScreenEvent()
}

@Composable
fun TransactionScreen(
    state: TransactionScreenState = TransactionScreenState(),
    event: (TransactionScreenEvent) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        containerColor = Color.White,
        topBar = {
            HeaderPage(
                title = "Transactions",
                leadingIconRes = Res.drawable.ic_arrow_left,
                trailingIconRes = Res.drawable.ic_search,
                onClickLeadingIcon = {
                    event(TransactionScreenEvent.OnClickBack)
                },
                onClickTrailingIcon = {
                    event(TransactionScreenEvent.OnClickSearch)
                },
            )
        },
        content = {
            TransactionScreenContent(
                modifier = Modifier.padding(it),
                state = state,
                event = event,
            )
        },
    )
}

@Composable
private fun TransactionScreenContent(
    modifier: Modifier,
    state: TransactionScreenState,
    event: (TransactionScreenEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item {
            AccountBalanceCard(
                accountNumber = state.accountNumber,
                balance = state.accountBalance,
            )
        }
        item {
            ChipFlexbox(
                items = state.chipList,
                onChipClick = {
                    event(TransactionScreenEvent.OnClickChip(item = it))
                },
            )
        }
        items(state.transactionList) { section ->
            TransactionHistorySection(attribute = section)
        }
    }
}

@Preview
@Composable
private fun TransactionScreenPreview() {
    TransactionScreen(
        state = TransactionScreenState(
            accountNumber = "**** 1234",
            accountBalance = "$1,234.56",
            chipList = listOf(
                ChipAttribute(
                    leadingIconRes = Res.drawable.ic_date_picker,
                    label = "Last 30 days",
                    trailingIconRes = Res.drawable.ic_chevron_down,
                ),
                ChipAttribute(label = "All", isSelected = true),
                ChipAttribute(label = "Income"),
                ChipAttribute(label = "Expenses"),
            ),
            transactionList = listOf(
                TransactionHistorySectionAttribute(
                    date = "TODAY",
                    itemList = listOf(
                        TransactionResourceItemAttribute(
                            iconRes = Res.drawable.ic_cup_outline,
                            label = "Amazon.com",
                            description = "Online Purchase",
                            price = "$78.50",
                        ),
                        TransactionResourceItemAttribute(
                            iconRes = Res.drawable.ic_cup_outline,
                            label = "Starbucks",
                            description = "Coffee",
                            price = "$5.75",
                        ),
                    ),
                ),
                TransactionHistorySectionAttribute(
                    date = "YESTERDAY",
                    itemList = listOf(
                        TransactionResourceItemAttribute(
                            iconRes = Res.drawable.ic_cup_outline,
                            label = "Salary Deposit",
                            description = "Direct Deposit",
                            price = "$2,500.00",
                        ),
                    ),
                ),
                TransactionHistorySectionAttribute(
                    date = "OCT 28, 2023",
                    itemList = listOf(
                        TransactionResourceItemAttribute(
                            iconRes = Res.drawable.ic_cup_outline,
                            label = "United Airlines",
                            description = "Flight to SFO",
                            price = "$412.30",
                        ),
                        TransactionResourceItemAttribute(
                            iconRes = Res.drawable.ic_cup_outline,
                            label = "The Cheesecake Factory",
                            description = "Dinner",
                            price = "$95.20",
                        ),
                    ),
                ),
            ),
        ),
    )
}
