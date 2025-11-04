package com.invoicegenerator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.invoicegenerator.ui.components.GradientButton
import com.invoicegenerator.ui.theme.BackgroundLight
import com.invoicegenerator.ui.theme.PrimaryBlue
import com.invoicegenerator.ui.theme.TextPrimary
import com.invoicegenerator.ui.viewmodel.InvoiceViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateInvoiceScreen(
    onNavigateBack: () -> Unit,
    viewModel: InvoiceViewModel = hiltViewModel()
) {
    var clientName by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var dateIssued by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    
    var clientNameError by remember { mutableStateOf<String?>(null) }
    var amountError by remember { mutableStateOf<String?>(null) }
    var dateIssuedError by remember { mutableStateOf<String?>(null) }
    var dueDateError by remember { mutableStateOf<String?>(null) }
    
    val createState by viewModel.createState.collectAsState()
    val scrollState = rememberScrollState()
    
    // Handle success
    LaunchedEffect(createState.isSuccess) {
        if (createState.isSuccess) {
            onNavigateBack()
            viewModel.clearCreateState()
        }
    }
    
    // Show error snackbar
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(createState.error) {
        createState.error?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create Invoice",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BackgroundLight,
                    titleContentColor = TextPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Client Name Field
            OutlinedTextField(
                value = clientName,
                onValueChange = {
                    clientName = it
                    clientNameError = null
                },
                label = { Text("Client Name") },
                placeholder = { Text("Enter client name") },
                isError = clientNameError != null,
                supportingText = clientNameError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Amount Field
            OutlinedTextField(
                value = amount,
                onValueChange = {
                    amount = it
                    amountError = null
                },
                label = { Text("Amount") },
                placeholder = { Text("Enter amount") },
                isError = amountError != null,
                supportingText = amountError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                leadingIcon = { Text("₹", fontSize = 18.sp) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Date Issued Field
            OutlinedTextField(
                value = dateIssued,
                onValueChange = {
                    dateIssued = it
                    dateIssuedError = null
                },
                label = { Text("Date Issued") },
                placeholder = { Text("YYYY-MM-DD") },
                isError = dateIssuedError != null,
                supportingText = dateIssuedError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        // Set current date
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        dateIssued = sdf.format(Date())
                    }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Select date"
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Due Date Field
            OutlinedTextField(
                value = dueDate,
                onValueChange = {
                    dueDate = it
                    dueDateError = null
                },
                label = { Text("Due Date") },
                placeholder = { Text("YYYY-MM-DD") },
                isError = dueDateError != null,
                supportingText = dueDateError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        // Set date 30 days from now
                        val calendar = Calendar.getInstance()
                        calendar.add(Calendar.DAY_OF_MONTH, 30)
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        dueDate = sdf.format(calendar.time)
                    }) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Select date"
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = PrimaryBlue.copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Invoice Information",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Text(
                        text = "• Date format: YYYY-MM-DD (e.g., 2024-01-15)",
                        fontSize = 12.sp,
                        color = TextPrimary.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "• Amount should be a valid number",
                        fontSize = 12.sp,
                        color = TextPrimary.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "• Due date should be after issue date",
                        fontSize = 12.sp,
                        color = TextPrimary.copy(alpha = 0.7f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Create Button
            GradientButton(
                text = if (createState.isLoading) "Creating..." else "Create Invoice",
                onClick = {
                    // Validate inputs
                    var isValid = true
                    
                    if (clientName.isBlank()) {
                        clientNameError = "Client name is required"
                        isValid = false
                    }
                    
                    if (amount.isBlank()) {
                        amountError = "Amount is required"
                        isValid = false
                    } else {
                        amount.toDoubleOrNull() ?: run {
                            amountError = "Invalid amount"
                            isValid = false
                        }
                    }
                    
                    if (dateIssued.isBlank()) {
                        dateIssuedError = "Date issued is required"
                        isValid = false
                    } else if (!isValidDate(dateIssued)) {
                        dateIssuedError = "Invalid date format (use YYYY-MM-DD)"
                        isValid = false
                    }
                    
                    if (dueDate.isBlank()) {
                        dueDateError = "Due date is required"
                        isValid = false
                    } else if (!isValidDate(dueDate)) {
                        dueDateError = "Invalid date format (use YYYY-MM-DD)"
                        isValid = false
                    }
                    
                    if (isValid) {
                        viewModel.createInvoice(
                            clientName = clientName,
                            amount = amount.toDouble(),
                            dateIssued = dateIssued,
                            dueDate = dueDate
                        )
                    }
                },
                enabled = !createState.isLoading,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private fun isValidDate(date: String): Boolean {
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.isLenient = false
        sdf.parse(date)
        true
    } catch (e: Exception) {
        false
    }
}

