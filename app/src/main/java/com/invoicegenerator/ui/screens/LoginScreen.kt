package com.invoicegenerator.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.invoicegenerator.ui.components.CustomTextField
import com.invoicegenerator.ui.components.GradientButton
import com.invoicegenerator.ui.theme.*
import com.invoicegenerator.utils.ValidationUtils

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    
    val scrollState = rememberScrollState()
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            
            // App Logo
            AppLogo()
            
            Spacer(modifier = Modifier.height(48.dp))
            
            // Welcome Text
            Text(
                text = "Welcome Back",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                style = MaterialTheme.typography.headlineMedium
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Sign in to continue",
                fontSize = 16.sp,
                color = TextSecondary,
                style = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Email/Mobile Number Field
            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = ""
                },
                label = "Email / Mobile Number",
                placeholder = "Enter your email or mobile number",
                leadingIcon = Icons.Filled.Email,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isError = emailError.isNotEmpty(),
                errorMessage = emailError,
                enabled = !isLoading
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Password Field
            CustomTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = ""
                },
                label = "Password",
                placeholder = "Enter your password",
                leadingIcon = Icons.Filled.Lock,
                isPassword = true,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                onImeAction = {
                    // Trigger login when done is pressed
                    performLogin(
                        email = email,
                        password = password,
                        onEmailError = { emailError = it },
                        onPasswordError = { passwordError = it },
                        onSuccess = {
                            isLoading = true
                            onLoginSuccess()
                        }
                    )
                },
                isError = passwordError.isNotEmpty(),
                errorMessage = passwordError,
                enabled = !isLoading
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Forgot Password Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    color = PrimaryBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clickable(enabled = !isLoading) { onForgotPasswordClick() }
                        .padding(vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Login Button
            GradientButton(
                text = if (isLoading) "Logging in..." else "Login",
                onClick = {
                    performLogin(
                        email = email,
                        password = password,
                        onEmailError = { emailError = it },
                        onPasswordError = { passwordError = it },
                        onSuccess = {
                            isLoading = true
                            onLoginSuccess()
                        }
                    )
                },
                enabled = !isLoading
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Create Account Link
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account? ",
                    color = TextSecondary,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Create New Account",
                    color = PrimaryBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .clickable(enabled = !isLoading) { onCreateAccountClick() }
                        .padding(vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun AppLogo() {
    // Placeholder for app logo
    // Replace with actual logo using painterResource or Image
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(
                color = PrimaryBlue,
                shape = androidx.compose.foundation.shape.CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "IG",
            color = TextWhite,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

private fun performLogin(
    email: String,
    password: String,
    onEmailError: (String) -> Unit,
    onPasswordError: (String) -> Unit,
    onSuccess: () -> Unit
) {
    var hasError = false
    
    // Validate email
    if (email.isEmpty()) {
        onEmailError("Email or mobile number is required")
        hasError = true
    } else if (!ValidationUtils.isValidEmailOrPhone(email)) {
        onEmailError("Please enter a valid email or mobile number")
        hasError = true
    }
    
    // Validate password
    if (password.isEmpty()) {
        onPasswordError("Password is required")
        hasError = true
    } else if (password.length < 6) {
        onPasswordError("Password must be at least 6 characters")
        hasError = true
    }
    
    if (!hasError) {
        // Perform login logic here
        onSuccess()
    }
}

