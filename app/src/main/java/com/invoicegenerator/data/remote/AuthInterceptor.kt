package com.invoicegenerator.data.remote

import com.invoicegenerator.data.local.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        
        // Get token from TokenManager
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        
        // If token exists and request is not to auth endpoints, add Authorization header
        val newRequest = if (token != null && !request.url.encodedPath.contains("/auth/")) {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }
        
        return chain.proceed(newRequest)
    }
}

