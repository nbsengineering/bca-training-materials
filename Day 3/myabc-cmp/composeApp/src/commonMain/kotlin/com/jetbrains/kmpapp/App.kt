package com.jetbrains.kmpapp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jetbrains.kmpapp.ui.molecules.ChipAttribute
import com.jetbrains.kmpapp.ui.molecules.MenuCardAttribute
import com.jetbrains.kmpapp.ui.organisms.BillSectionResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.BottomNavBar
import com.jetbrains.kmpapp.ui.organisms.BottomNavBarItem
import com.jetbrains.kmpapp.ui.organisms.FavoriteSectionResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.MenuResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.NotificationResourceItemAttribute
import com.jetbrains.kmpapp.ui.organisms.NotificationSectionAttribute
import com.jetbrains.kmpapp.ui.organisms.SettingMenuSectionAttribute
import com.jetbrains.kmpapp.ui.organisms.TransactionHistorySectionAttribute
import com.jetbrains.kmpapp.ui.organisms.TransactionResourceItemAttribute
import com.jetbrains.kmpapp.ui.screens.DashboardScreen
import com.jetbrains.kmpapp.ui.screens.DashboardScreenState
import com.jetbrains.kmpapp.ui.screens.NotificationScreen
import com.jetbrains.kmpapp.ui.screens.NotificationScreenState
import com.jetbrains.kmpapp.ui.screens.SettingScreen
import com.jetbrains.kmpapp.ui.screens.SettingScreenState
import com.jetbrains.kmpapp.ui.screens.TransactionScreen
import com.jetbrains.kmpapp.ui.screens.TransactionScreenState
import myabc_cmp.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}

@Composable
private fun AppNavigation() {
    val navController = rememberNavController()
    val navItems = remember { bottomNavDestinations.map { it.toNavBarItem() } }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomNavDestination.Dashboard.route

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        containerColor = Color.White,
        bottomBar = {
            BottomNavBar(
                items = navItems,
                selectedRoute = currentRoute,
                onItemSelected = { destination ->
                    if (destination.route == currentRoute) return@BottomNavBar
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavDestination.Dashboard.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(BottomNavDestination.Dashboard.route) {
                DashboardScreen(state = dashboardPreviewState)
            }
            composable(BottomNavDestination.Transactions.route) {
                TransactionScreen(state = transactionPreviewState)
            }
            composable(BottomNavDestination.Notifications.route) {
                NotificationScreen(state = notificationPreviewState)
            }
            composable(BottomNavDestination.Settings.route) {
                SettingScreen(state = settingPreviewState)
            }
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}

/** Dummy State Data Screen */
private val dashboardPreviewState = DashboardScreenState(
    userAvatar = Res.drawable.img_man_side_view,
    userName = "Alex",
    totalBalance = "$12,345.67",
    accountNumber = "...1234",
    menuList = listOf(
        MenuCardAttribute(
            iconRes = Res.drawable.ic_transfer,
            label = "Transfer",
        ),
        MenuCardAttribute(
            iconRes = Res.drawable.ic_wallet,
            label = "Deposit",
        ),
        MenuCardAttribute(
            iconRes = Res.drawable.ic_receipt,
            label = "Pay Bills",
        ),
        MenuCardAttribute(
            iconRes = Res.drawable.ic_menu_outline,
            label = "More",
        ),
    ),
    paymentList = listOf(
        BillSectionResourceItemAttribute(
            iconRes = Res.drawable.ic_netflix_square,
            label = "Netflix",
            description = "Due: Oct 28",
            price = "$15.49",
            iconBackgroundColor = Color.Magenta,
        ),
        BillSectionResourceItemAttribute(
            iconRes = Res.drawable.ic_light_bulb,
            label = "City Power",
            description = "Due: Nov 02",
            price = "$78.20",
            iconBackgroundColor = Color.Yellow,
        ),
    ),
    favoriteList = listOf(
        FavoriteSectionResourceItemAttribute(
            imageRes = Res.drawable.img_mom_portrait,
            label = "Mom",
            description = "Last transfer: $50.00",
        ),
        FavoriteSectionResourceItemAttribute(
            imageRes = Res.drawable.img_landlord_portrait,
            label = "Landlord",
            description = "Last transfer: $1200.00",
        ),
    ),
)

private val transactionPreviewState = TransactionScreenState(
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
    ),
)

private val notificationPreviewState = NotificationScreenState(
    notificationList = listOf(
        NotificationSectionAttribute(
            date = "TODAY",
            itemList = listOf(
                NotificationResourceItemAttribute(
                    iconRes = Res.drawable.ic_card_outline,
                    label = "Card Purchase",
                    description = "$50.00 at Starbucks",
                    timestamp = "10:45 AM",
                ),
                NotificationResourceItemAttribute(
                    iconRes = Res.drawable.ic_card_outline,
                    label = "New Device Login",
                    description = "From a device in London, UK",
                    timestamp = "9:30 AM",
                ),
            ),
        ),
        NotificationSectionAttribute(
            date = "YESTERDAY",
            itemList = listOf(
                NotificationResourceItemAttribute(
                    iconRes = Res.drawable.ic_card_outline,
                    label = "New Cashback Offer",
                    description = "Earn 5% back on dining.",
                    timestamp = "4:15 PM",
                ),
            ),
        ),
    ),
)

private val settingPreviewState = SettingScreenState(
    userAvatar = Res.drawable.img_man_side_view,
    userName = "Alex Doe",
    userEmail = "alex.doe@email.com",
    settingMenuList = listOf(
        SettingMenuSectionAttribute(
            title = "PERSONAL INFORMATION",
            itemList = listOf(
                MenuResourceItemAttribute(
                    iconRes = Res.drawable.ic_setting,
                    label = "Contact Details",
                    description = "Manage your phone and email",
                ),
                MenuResourceItemAttribute(
                    iconRes = Res.drawable.ic_setting,
                    label = "Mailing Address",
                ),
            ),
        ),
        SettingMenuSectionAttribute(
            title = "SECURITY",
            itemList = listOf(
                MenuResourceItemAttribute(
                    iconRes = Res.drawable.ic_setting,
                    label = "Change Password",
                ),
                MenuResourceItemAttribute(
                    iconRes = Res.drawable.ic_setting,
                    label = "Manage Biometrics",
                ),
            ),
        ),
    ),
)

/** Navigation */
private sealed class BottomNavDestination(
    val route: String,
    val label: String,
    val iconRes: DrawableResource,
) {
    data object Dashboard : BottomNavDestination("dashboard", "Home", Res.drawable.ic_home_outline)

    data object Transactions :
        BottomNavDestination("transactions", "Transactions", Res.drawable.ic_history)

    data object Notifications :
        BottomNavDestination("notifications", "Notifications", Res.drawable.ic_notification_fill)

    data object Settings : BottomNavDestination("settings", "Settings", Res.drawable.ic_setting)
}

private val bottomNavDestinations = listOf(
    BottomNavDestination.Dashboard,
    BottomNavDestination.Transactions,
    BottomNavDestination.Notifications,
    BottomNavDestination.Settings,
)

private fun BottomNavDestination.toNavBarItem() = BottomNavBarItem(
    route = route,
    label = label,
    iconRes = iconRes,
)