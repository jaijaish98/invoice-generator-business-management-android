package com.invoicegenerator.data.repository

import com.invoicegenerator.data.model.CreateQuotationRequest
import com.invoicegenerator.data.model.PagedResponse
import com.invoicegenerator.data.model.Quotation
import com.invoicegenerator.data.model.UpdateQuotationRequest
import com.invoicegenerator.data.remote.QuotationApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuotationRepository @Inject constructor(
    private val quotationApiService: QuotationApiService
) {
    suspend fun getQuotations(page: Int = 0, size: Int = 10): Flow<Result<PagedResponse<Quotation>>> = flow {
        try {
            emit(Result.Loading)
            val response = quotationApiService.getQuotations(page, size)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to fetch quotations"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun createQuotation(request: CreateQuotationRequest): Flow<Result<Quotation>> = flow {
        try {
            emit(Result.Loading)
            val response = quotationApiService.createQuotation(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to create quotation"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun updateQuotation(id: String, status: String): Flow<Result<Quotation>> = flow {
        try {
            emit(Result.Loading)
            val response = quotationApiService.updateQuotation(id, UpdateQuotationRequest(status))
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to update quotation"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun deleteQuotation(id: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = quotationApiService.deleteQuotation(id)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(Unit))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to delete quotation"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
}

