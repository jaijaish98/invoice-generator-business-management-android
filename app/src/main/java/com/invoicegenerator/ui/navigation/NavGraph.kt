package com.invoicegenerator.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.invoicegenerator.ui.screens.CreateEWayBillScreen
import com.invoicegenerator.ui.screens.CreateInvoiceScreen
import com.invoicegenerator.ui.screens.DashboardScreen
import com.invoicegenerator.ui.screens.LoginScreen

// Navigation routes
object Routes {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val CREATE_INVOICE = "create_invoice"
    const val GENERATE_EWAY_BILL = "generate_eway_bill"
    const val CREATE_QUOTATION = "create_quotation"
    const val PAYMENT_RECEIPT = "payment_receipt"
    const val MANAGE_CUSTOMERS = "manage_customers"
    const val MANAGE_PRODUCTS = "manage_products"
    const val REPORTS = "reports"
    const val BUSINESS_PROFILE = "business_profile"
    const val PROFILE = "profile"
    const val FORGOT_PASSWORD = "forgot_password"
    const val CREATE_ACCOUNT = "create_account"
}

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Login Screen
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.DASHBOARD) {
                        // Clear back stack so user can't go back to login
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onCreateAccountClick = {
                    navController.navigate(Routes.CREATE_ACCOUNT)
                },
                onForgotPasswordClick = {
                    navController.navigate(Routes.FORGOT_PASSWORD)
                }
            )
        }
        
        // Dashboard Screen
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                onFeatureClick = { route ->
                    navController.navigate(route)
                },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }
        
        // Feature Screens
        composable(Routes.CREATE_INVOICE) {
            CreateInvoiceScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }

        composable(Routes.GENERATE_EWAY_BILL) {
            CreateEWayBillScreen(
                onNavigateBack = { navController.navigateUp() }
            )
        }
        
        composable(Routes.CREATE_QUOTATION) {
            PlaceholderScreen(title = "Create Quotation", navController = navController)
        }
        
        composable(Routes.PAYMENT_RECEIPT) {
            PlaceholderScreen(title = "Payment Receipt", navController = navController)
        }
        
        composable(Routes.MANAGE_CUSTOMERS) {
            PlaceholderScreen(title = "Manage Customers", navController = navController)
        }
        
        composable(Routes.MANAGE_PRODUCTS) {
            PlaceholderScreen(title = "Manage Products", navController = navController)
        }
        
        composable(Routes.REPORTS) {
            PlaceholderScreen(title = "Reports", navController = navController)
        }
        
        composable(Routes.BUSINESS_PROFILE) {
            PlaceholderScreen(title = "Business Profile", navController = navController)
        }
        
        composable(Routes.PROFILE) {
            PlaceholderScreen(title = "Profile", navController = navController)
        }
        
        composable(Routes.FORGOT_PASSWORD) {
            PlaceholderScreen(title = "Forgot Password", navController = navController)
        }
        
        composable(Routes.CREATE_ACCOUNT) {
            PlaceholderScreen(title = "Create Account", navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceholderScreen(
    title: String,
    navController: NavHostController
) {
    androidx.compose.material3.Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { androidx.compose.material3.Text(title) },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.navigateUp() }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = com.invoicegenerator.ui.theme.PrimaryBlue,
                    titleContentColor = com.invoicegenerator.ui.theme.TextWhite,
                    navigationIconContentColor = com.invoicegenerator.ui.theme.TextWhite
                )
            )
        }
    ) { paddingValues ->
        androidx.compose.foundation.layout.Box(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            androidx.compose.material3.Text(
                text = "$title Screen\n(To be implemented)",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = com.invoicegenerator.ui.theme.TextSecondary
            )
        }
    }
}

