package com.invoicegenerator.data.model

import com.google.gson.annotations.SerializedName

data class Quotation(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("userId")
    val userId: String,
    
    @SerializedName("clientName")
    val clientName: String,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("validUntil")
    val validUntil: String,
    
    @SerializedName("status")
    val status: QuotationStatus,
    
    @SerializedName("description")
    val description: String?,
    
    @SerializedName("createdAt")
    val createdAt: String,
    
    @SerializedName("updatedAt")
    val updatedAt: String
)

enum class QuotationStatus {
    @SerializedName("DRAFT")
    DRAFT,
    
    @SerializedName("SENT")
    SENT,
    
    @SerializedName("ACCEPTED")
    ACCEPTED,
    
    @SerializedName("REJECTED")
    REJECTED,
    
    @SerializedName("EXPIRED")
    EXPIRED
}

data class CreateQuotationRequest(
    @SerializedName("clientName")
    val clientName: String,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("validUntil")
    val validUntil: String,
    
    @SerializedName("status")
    val status: String = "DRAFT",
    
    @SerializedName("description")
    val description: String?
)

data class UpdateQuotationRequest(
    @SerializedName("status")
    val status: String
)

