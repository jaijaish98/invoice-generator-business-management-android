package com.invoicegenerator.data.repository

import com.invoicegenerator.data.local.TokenManager
import com.invoicegenerator.data.model.AuthResponse
import com.invoicegenerator.data.model.LoginRequest
import com.invoicegenerator.data.model.RegisterRequest
import com.invoicegenerator.data.model.User
import com.invoicegenerator.data.remote.AuthApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApiService: AuthApiService,
    private val tokenManager: TokenManager
) {
    suspend fun register(name: String, email: String, password: String): Flow<Result<AuthResponse>> = flow {
        try {
            emit(Result.Loading)
            val response = authApiService.register(RegisterRequest(name, email, password))
            
            if (response.isSuccessful && response.body()?.success == true) {
                val authResponse = response.body()!!.data!!
                // Save token and user info
                tokenManager.saveToken(authResponse.token)
                tokenManager.saveUserInfo(
                    authResponse.user.id,
                    authResponse.user.name,
                    authResponse.user.email
                )
                emit(Result.Success(authResponse))
            } else {
                val errorMessage = response.body()?.message ?: "Registration failed"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun login(email: String, password: String): Flow<Result<AuthResponse>> = flow {
        try {
            emit(Result.Loading)
            val response = authApiService.login(LoginRequest(email, password))
            
            if (response.isSuccessful && response.body()?.success == true) {
                val authResponse = response.body()!!.data!!
                // Save token and user info
                tokenManager.saveToken(authResponse.token)
                tokenManager.saveUserInfo(
                    authResponse.user.id,
                    authResponse.user.name,
                    authResponse.user.email
                )
                emit(Result.Success(authResponse))
            } else {
                val errorMessage = response.body()?.message ?: "Login failed"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun getCurrentUser(): Flow<Result<User>> = flow {
        try {
            emit(Result.Loading)
            val response = authApiService.getCurrentUser()
            
            if (response.isSuccessful && response.body()?.success == true) {
                val user = response.body()!!.data!!
                emit(Result.Success(user))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to get user info"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun logout() {
        tokenManager.clearAll()
    }
    
    fun isLoggedIn(): Flow<Boolean> = flow {
        tokenManager.getToken().collect { token ->
            emit(!token.isNullOrEmpty())
        }
    }
}

