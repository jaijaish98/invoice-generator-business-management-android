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
import com.invoicegenerator.ui.viewmodel.EWayBillViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEWayBillScreen(
    onNavigateBack: () -> Unit,
    viewModel: EWayBillViewModel = hiltViewModel()
) {
    var billNumber by remember { mutableStateOf("") }
    var consignorName by remember { mutableStateOf("") }
    var consigneeName by remember { mutableStateOf("") }
    var goodsValue by remember { mutableStateOf("") }
    var transportMode by remember { mutableStateOf("ROAD") }
    var vehicleNumber by remember { mutableStateOf("") }
    var distanceKm by remember { mutableStateOf("") }
    var validFrom by remember { mutableStateOf("") }
    var validUntil by remember { mutableStateOf("") }
    var expandedTransportMode by remember { mutableStateOf(false) }
    
    // Error states
    var billNumberError by remember { mutableStateOf<String?>(null) }
    var consignorNameError by remember { mutableStateOf<String?>(null) }
    var consigneeNameError by remember { mutableStateOf<String?>(null) }
    var goodsValueError by remember { mutableStateOf<String?>(null) }
    var vehicleNumberError by remember { mutableStateOf<String?>(null) }
    var distanceKmError by remember { mutableStateOf<String?>(null) }
    var validFromError by remember { mutableStateOf<String?>(null) }
    var validUntilError by remember { mutableStateOf<String?>(null) }
    
    val createState by viewModel.createState.collectAsState()
    val scrollState = rememberScrollState()
    
    val transportModes = listOf("ROAD", "RAIL", "AIR", "SHIP")
    
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
                        text = "Generate E-Way Bill",
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
            // Bill Number Field
            OutlinedTextField(
                value = billNumber,
                onValueChange = {
                    billNumber = it
                    billNumberError = null
                },
                label = { Text("Bill Number") },
                placeholder = { Text("Enter bill number") },
                isError = billNumberError != null,
                supportingText = billNumberError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Consignor Name Field
            OutlinedTextField(
                value = consignorName,
                onValueChange = {
                    consignorName = it
                    consignorNameError = null
                },
                label = { Text("Consignor Name") },
                placeholder = { Text("Enter consignor name") },
                isError = consignorNameError != null,
                supportingText = consignorNameError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Consignee Name Field
            OutlinedTextField(
                value = consigneeName,
                onValueChange = {
                    consigneeName = it
                    consigneeNameError = null
                },
                label = { Text("Consignee Name") },
                placeholder = { Text("Enter consignee name") },
                isError = consigneeNameError != null,
                supportingText = consigneeNameError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Goods Value Field
            OutlinedTextField(
                value = goodsValue,
                onValueChange = {
                    goodsValue = it
                    goodsValueError = null
                },
                label = { Text("Goods Value") },
                placeholder = { Text("Enter goods value") },
                isError = goodsValueError != null,
                supportingText = goodsValueError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                leadingIcon = { Text("₹", fontSize = 18.sp) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Transport Mode Dropdown
            ExposedDropdownMenuBox(
                expanded = expandedTransportMode,
                onExpandedChange = { expandedTransportMode = !expandedTransportMode }
            ) {
                OutlinedTextField(
                    value = transportMode,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Transport Mode") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTransportMode) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryBlue,
                        focusedLabelColor = PrimaryBlue
                    )
                )
                ExposedDropdownMenu(
                    expanded = expandedTransportMode,
                    onDismissRequest = { expandedTransportMode = false }
                ) {
                    transportModes.forEach { mode ->
                        DropdownMenuItem(
                            text = { Text(mode) },
                            onClick = {
                                transportMode = mode
                                expandedTransportMode = false
                            }
                        )
                    }
                }
            }
            
            // Vehicle Number Field
            OutlinedTextField(
                value = vehicleNumber,
                onValueChange = {
                    vehicleNumber = it.uppercase()
                    vehicleNumberError = null
                },
                label = { Text("Vehicle Number") },
                placeholder = { Text("e.g., MH12AB1234") },
                isError = vehicleNumberError != null,
                supportingText = vehicleNumberError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Distance Field
            OutlinedTextField(
                value = distanceKm,
                onValueChange = {
                    distanceKm = it
                    distanceKmError = null
                },
                label = { Text("Distance (KM)") },
                placeholder = { Text("Enter distance in kilometers") },
                isError = distanceKmError != null,
                supportingText = distanceKmError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            
            // Valid From Field
            OutlinedTextField(
                value = validFrom,
                onValueChange = {
                    validFrom = it
                    validFromError = null
                },
                label = { Text("Valid From") },
                placeholder = { Text("YYYY-MM-DD") },
                isError = validFromError != null,
                supportingText = validFromError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        validFrom = sdf.format(Date())
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
            
            // Valid Until Field
            OutlinedTextField(
                value = validUntil,
                onValueChange = {
                    validUntil = it
                    validUntilError = null
                },
                label = { Text("Valid Until") },
                placeholder = { Text("YYYY-MM-DD") },
                isError = validUntilError != null,
                supportingText = validUntilError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        val calendar = Calendar.getInstance()
                        calendar.add(Calendar.DAY_OF_MONTH, 7)
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        validUntil = sdf.format(calendar.time)
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
                        text = "E-Way Bill Information",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Text(
                        text = "• Date format: YYYY-MM-DD",
                        fontSize = 12.sp,
                        color = TextPrimary.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "• Vehicle number format: STATE##XX####",
                        fontSize = 12.sp,
                        color = TextPrimary.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "• Valid until should be after valid from date",
                        fontSize = 12.sp,
                        color = TextPrimary.copy(alpha = 0.7f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Generate Button
            GradientButton(
                text = if (createState.isLoading) "Generating..." else "Generate E-Way Bill",
                onClick = {
                    // Validate inputs
                    var isValid = true
                    
                    if (billNumber.isBlank()) {
                        billNumberError = "Bill number is required"
                        isValid = false
                    }
                    
                    if (consignorName.isBlank()) {
                        consignorNameError = "Consignor name is required"
                        isValid = false
                    }
                    
                    if (consigneeName.isBlank()) {
                        consigneeNameError = "Consignee name is required"
                        isValid = false
                    }
                    
                    if (goodsValue.isBlank()) {
                        goodsValueError = "Goods value is required"
                        isValid = false
                    } else {
                        goodsValue.toDoubleOrNull() ?: run {
                            goodsValueError = "Invalid goods value"
                            isValid = false
                        }
                    }
                    
                    if (vehicleNumber.isBlank()) {
                        vehicleNumberError = "Vehicle number is required"
                        isValid = false
                    }
                    
                    if (distanceKm.isBlank()) {
                        distanceKmError = "Distance is required"
                        isValid = false
                    } else {
                        distanceKm.toIntOrNull() ?: run {
                            distanceKmError = "Invalid distance"
                            isValid = false
                        }
                    }
                    
                    if (validFrom.isBlank()) {
                        validFromError = "Valid from date is required"
                        isValid = false
                    } else if (!isValidDate(validFrom)) {
                        validFromError = "Invalid date format"
                        isValid = false
                    }
                    
                    if (validUntil.isBlank()) {
                        validUntilError = "Valid until date is required"
                        isValid = false
                    } else if (!isValidDate(validUntil)) {
                        validUntilError = "Invalid date format"
                        isValid = false
                    }
                    
                    if (isValid) {
                        viewModel.createEWayBill(
                            billNumber = billNumber,
                            consignorName = consignorName,
                            consigneeName = consigneeName,
                            goodsValue = goodsValue.toDouble(),
                            transportMode = transportMode,
                            vehicleNumber = vehicleNumber,
                            distanceKm = distanceKm.toInt(),
                            validFrom = validFrom,
                            validUntil = validUntil
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

