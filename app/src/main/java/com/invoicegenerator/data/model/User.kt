package com.invoicegenerator.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("role")
    val role: String,
    
    @SerializedName("createdAt")
    val createdAt: String
)

data class AuthResponse(
    @SerializedName("token")
    val token: String,
    
    @SerializedName("user")
    val user: User
)

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    
    @SerializedName("password")
    val password: String
)

data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("password")
    val password: String
)

