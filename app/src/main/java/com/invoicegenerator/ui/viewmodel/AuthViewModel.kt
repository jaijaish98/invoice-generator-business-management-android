package com.invoicegenerator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invoicegenerator.data.model.AuthResponse
import com.invoicegenerator.data.repository.AuthRepository
import com.invoicegenerator.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthState(
    val isLoading: Boolean = false,
    val authResponse: AuthResponse? = null,
    val error: String? = null,
    val isLoggedIn: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState.asStateFlow()
    
    init {
        checkLoginStatus()
    }
    
    private fun checkLoginStatus() {
        viewModelScope.launch {
            authRepository.isLoggedIn().collect { isLoggedIn ->
                _authState.value = _authState.value.copy(isLoggedIn = isLoggedIn)
            }
        }
    }
    
    fun login(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _authState.value = AuthState(isLoading = true)
                    }
                    is Result.Success -> {
                        _authState.value = AuthState(
                            isLoading = false,
                            authResponse = result.data,
                            isLoggedIn = true
                        )
                    }
                    is Result.Error -> {
                        _authState.value = AuthState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(name, email, password).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _authState.value = AuthState(isLoading = true)
                    }
                    is Result.Success -> {
                        _authState.value = AuthState(
                            isLoading = false,
                            authResponse = result.data,
                            isLoggedIn = true
                        )
                    }
                    is Result.Error -> {
                        _authState.value = AuthState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            _authState.value = AuthState(isLoggedIn = false)
        }
    }
    
    fun clearError() {
        _authState.value = _authState.value.copy(error = null)
    }
}

