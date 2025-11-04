package com.invoicegenerator.data.repository

import com.invoicegenerator.data.model.CreateInvoiceRequest
import com.invoicegenerator.data.model.Invoice
import com.invoicegenerator.data.model.PagedResponse
import com.invoicegenerator.data.model.UpdateInvoiceRequest
import com.invoicegenerator.data.remote.InvoiceApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoiceRepository @Inject constructor(
    private val invoiceApiService: InvoiceApiService
) {
    suspend fun getInvoices(page: Int = 0, size: Int = 10): Flow<Result<PagedResponse<Invoice>>> = flow {
        try {
            emit(Result.Loading)
            val response = invoiceApiService.getInvoices(page, size)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val pagedResponse = response.body()!!.data!!
                emit(Result.Success(pagedResponse))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to fetch invoices"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun getInvoiceById(id: String): Flow<Result<Invoice>> = flow {
        try {
            emit(Result.Loading)
            val response = invoiceApiService.getInvoiceById(id)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val invoice = response.body()!!.data!!
                emit(Result.Success(invoice))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to fetch invoice"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun createInvoice(request: CreateInvoiceRequest): Flow<Result<Invoice>> = flow {
        try {
            emit(Result.Loading)
            val response = invoiceApiService.createInvoice(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                val invoice = response.body()!!.data!!
                emit(Result.Success(invoice))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to create invoice"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun updateInvoice(id: String, status: String): Flow<Result<Invoice>> = flow {
        try {
            emit(Result.Loading)
            val response = invoiceApiService.updateInvoice(id, UpdateInvoiceRequest(status))
            
            if (response.isSuccessful && response.body()?.success == true) {
                val invoice = response.body()!!.data!!
                emit(Result.Success(invoice))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to update invoice"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun deleteInvoice(id: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = invoiceApiService.deleteInvoice(id)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(Unit))
            } else {
                val errorMessage = response.body()?.message ?: "Failed to delete invoice"
                emit(Result.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
}

