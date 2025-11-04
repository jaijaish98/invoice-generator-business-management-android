package com.invoicegenerator.data.repository

import com.invoicegenerator.data.model.CreateEWayBillRequest
import com.invoicegenerator.data.model.EWayBill
import com.invoicegenerator.data.model.PagedResponse
import com.invoicegenerator.data.remote.EWayBillApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EWayBillRepository @Inject constructor(
    private val eWayBillApiService: EWayBillApiService
) {
    suspend fun getEWayBills(page: Int = 0, size: Int = 10): Flow<Result<PagedResponse<EWayBill>>> = flow {
        try {
            emit(Result.Loading)
            val response = eWayBillApiService.getEWayBills(page, size)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to fetch e-way bills"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun createEWayBill(request: CreateEWayBillRequest): Flow<Result<EWayBill>> = flow {
        try {
            emit(Result.Loading)
            val response = eWayBillApiService.createEWayBill(request)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(response.body()!!.data!!))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to create e-way bill"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
    
    suspend fun deleteEWayBill(id: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            val response = eWayBillApiService.deleteEWayBill(id)
            
            if (response.isSuccessful && response.body()?.success == true) {
                emit(Result.Success(Unit))
            } else {
                emit(Result.Error(response.body()?.message ?: "Failed to delete e-way bill"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "An error occurred", e))
        }
    }
}

