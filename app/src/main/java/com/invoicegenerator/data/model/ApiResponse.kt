package com.invoicegenerator.data.model

import com.google.gson.annotations.SerializedName

/**
 * Generic API Response wrapper
 */
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    
    @SerializedName("data")
    val data: T?,
    
    @SerializedName("message")
    val message: String,
    
    @SerializedName("timestamp")
    val timestamp: String
)

/**
 * Paginated response wrapper
 */
data class PagedResponse<T>(
    @SerializedName("content")
    val content: List<T>,
    
    @SerializedName("pageable")
    val pageable: Pageable,
    
    @SerializedName("totalElements")
    val totalElements: Int,
    
    @SerializedName("totalPages")
    val totalPages: Int,
    
    @SerializedName("last")
    val last: Boolean
)

data class Pageable(
    @SerializedName("pageNumber")
    val pageNumber: Int,
    
    @SerializedName("pageSize")
    val pageSize: Int
)

