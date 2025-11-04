package com.invoicegenerator.data.model

import com.google.gson.annotations.SerializedName

data class EWayBill(
    @SerializedName("id")
    val id: String,
    
    @SerializedName("userId")
    val userId: String,
    
    @SerializedName("billNumber")
    val billNumber: String,
    
    @SerializedName("consignorName")
    val consignorName: String,
    
    @SerializedName("consigneeName")
    val consigneeName: String,
    
    @SerializedName("goodsValue")
    val goodsValue: Double,
    
    @SerializedName("transportMode")
    val transportMode: TransportMode,
    
    @SerializedName("vehicleNumber")
    val vehicleNumber: String,
    
    @SerializedName("distanceKm")
    val distanceKm: Int,
    
    @SerializedName("validFrom")
    val validFrom: String,
    
    @SerializedName("validUntil")
    val validUntil: String,
    
    @SerializedName("status")
    val status: EWayBillStatus,
    
    @SerializedName("createdAt")
    val createdAt: String,
    
    @SerializedName("updatedAt")
    val updatedAt: String
)

enum class TransportMode {
    @SerializedName("ROAD")
    ROAD,
    
    @SerializedName("RAIL")
    RAIL,
    
    @SerializedName("AIR")
    AIR,
    
    @SerializedName("SHIP")
    SHIP
}

enum class EWayBillStatus {
    @SerializedName("ACTIVE")
    ACTIVE,
    
    @SerializedName("EXPIRED")
    EXPIRED,
    
    @SerializedName("CANCELLED")
    CANCELLED
}

data class CreateEWayBillRequest(
    @SerializedName("billNumber")
    val billNumber: String,
    
    @SerializedName("consignorName")
    val consignorName: String,
    
    @SerializedName("consigneeName")
    val consigneeName: String,
    
    @SerializedName("goodsValue")
    val goodsValue: Double,
    
    @SerializedName("transportMode")
    val transportMode: String,
    
    @SerializedName("vehicleNumber")
    val vehicleNumber: String,
    
    @SerializedName("distanceKm")
    val distanceKm: Int,
    
    @SerializedName("validFrom")
    val validFrom: String,
    
    @SerializedName("validUntil")
    val validUntil: String,
    
    @SerializedName("status")
    val status: String = "ACTIVE"
)

