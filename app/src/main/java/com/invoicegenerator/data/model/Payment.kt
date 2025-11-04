package com.invoicegenerator.data.model

import com.google.gson.annotations.SerializedName

data class Payment(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("userId")
    val userId: String,
    
    @SerializedName("invoiceId")
    val invoiceId: String?,
    
    @SerializedName("payerName")
    val payerName: String,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("paymentDate")
    val paymentDate: String,
    
    @SerializedName("paymentMethod")
    val paymentMethod: PaymentMethod,
    
    @SerializedName("transactionReference")
    val transactionReference: String,
    
    @SerializedName("notes")
    val notes: String?,
    
    @SerializedName("createdAt")
    val createdAt: String,
    
    @SerializedName("updatedAt")
    val updatedAt: String
)

enum class PaymentMethod {
    @SerializedName("CASH")
    CASH,
    
    @SerializedName("BANK_TRANSFER")
    BANK_TRANSFER,
    
    @SerializedName("CREDIT_CARD")
    CREDIT_CARD,
    
    @SerializedName("DEBIT_CARD")
    DEBIT_CARD,
    
    @SerializedName("UPI")
    UPI,
    
    @SerializedName("CHEQUE")
    CHEQUE
}

data class CreatePaymentRequest(
    @SerializedName("invoiceId")
    val invoiceId: String?,
    
    @SerializedName("payerName")
    val payerName: String,
    
    @SerializedName("amount")
    val amount: Double,
    
    @SerializedName("paymentDate")
    val paymentDate: String,
    
    @SerializedName("paymentMethod")
    val paymentMethod: String,
    
    @SerializedName("transactionReference")
    val transactionReference: String,
    
    @SerializedName("notes")
    val notes: String?
)

