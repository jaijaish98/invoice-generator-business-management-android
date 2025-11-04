package com.invoicegenerator.data.repository

import com.invoicegenerator.data.model.CreatePaymentRequest
import com.invoicegenerator.data.model.PagedResponse
import com.invoicegenerator.data.model.Payment
import com.invoicegenerator.data.remote.PaymentApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor(
    private val paymentApiService: PaymentApiService
) {
    suspend fun getPayments(page: Int = 0, size: Int = 10): Flow<Result<PagedResponse<Payment>>> = flow {
        try {
            emit(Result.Loading)
            val response = paymentApiService.getPayments(page, size)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to fetch payments"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun createPayment(request: CreatePaymentRequest): Flow<Result<Payment>> = flow {
        try {
            emit(Result.Loading)
            val response = paymentApiService.createPayment(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to create payment"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun deletePayment(id: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = paymentApiService.deletePayment(id)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(Unit))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to delete payment"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
}

