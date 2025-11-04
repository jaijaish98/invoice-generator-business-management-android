package com.invoicegenerator.data.model

import com.google.gson.annotations.SerializedName

data class Invoice(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("userId")
    val userId: String,
    
    @SerializedName("clientName")
    val clientName: String,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("dateIssued")
    val dateIssued: String,
    
    @SerializedName("dueDate")
    val dueDate: String,
    
    @SerializedName("status")
    val status: InvoiceStatus,
    
    @SerializedName("createdAt")
    val createdAt: String,
    
    @SerializedName("updatedAt")
    val updatedAt: String
)

enum class InvoiceStatus {
    @SerializedName("PENDING")
    PENDING,
    
    @SerializedName("PAID")
    PAID,
    
    @SerializedName("OVERDUE")
    OVERDUE,
    
    @SerializedName("CANCELLED")
    CANCELLED
}

data class CreateInvoiceRequest(
    @SerializedName("clientName")
    val clientName: String,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("dateIssued")
    val dateIssued: String,
    
    @SerializedName("dueDate")
    val dueDate: String,
    
    @SerializedName("status")
    val status: String = "PENDING"
)

data class UpdateInvoiceRequest(
    @SerializedName("status")
    val status: String
)

