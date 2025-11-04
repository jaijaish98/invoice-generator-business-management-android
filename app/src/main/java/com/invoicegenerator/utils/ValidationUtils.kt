package com.invoicegenerator.utils

import android.util.Patterns

object ValidationUtils {
    
    /**
     * Validates if the input is a valid email or phone number
     */
    fun isValidEmailOrPhone(input: String): Boolean {
        return isValidEmail(input) || isValidPhoneNumber(input)
    }
    
    /**
     * Validates if the input is a valid email address
     */
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    /**
     * Validates if the input is a valid phone number
     * Accepts 10 digit numbers with optional country code
     */
    fun isValidPhoneNumber(phone: String): Boolean {
        val phonePattern = "^[+]?[0-9]{10,13}$".toRegex()
        return phone.isNotEmpty() && phonePattern.matches(phone.replace("\\s".toRegex(), ""))
    }
    
    /**
     * Validates password strength
     */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
    
    /**
     * Validates if password is strong (contains uppercase, lowercase, number, special char)
     */
    fun isStrongPassword(password: String): Boolean {
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()
        return passwordPattern.matches(password)
    }
}

