package com.invoicegenerator.data.remote

import com.invoicegenerator.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface AuthApiService {
    @POST("api/v1/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<AuthResponse>>
    
    @POST("api/v1/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<AuthResponse>>
    
    @GET("api/v1/auth/me")
    suspend fun getCurrentUser(): Response<ApiResponse<User>>
}

interface InvoiceApiService {
    @GET("api/v1/invoices")
    suspend fun getInvoices(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String = "createdAt,desc"
    ): Response<ApiResponse<PagedResponse<Invoice>>>
    
    @GET("api/v1/invoices/{id}")
    suspend fun getInvoiceById(@Path("id") id: String): Response<ApiResponse<Invoice>>
    
    @POST("api/v1/invoices")
    suspend fun createInvoice(@Body request: CreateInvoiceRequest): Response<ApiResponse<Invoice>>
    
    @PUT("api/v1/invoices/{id}")
    suspend fun updateInvoice(
        @Path("id") id: String,
        @Body request: UpdateInvoiceRequest
    ): Response<ApiResponse<Invoice>>
    
    @DELETE("api/v1/invoices/{id}")
    suspend fun deleteInvoice(@Path("id") id: String): Response<ApiResponse<Unit>>
}

interface QuotationApiService {
    @GET("api/v1/quotations")
    suspend fun getQuotations(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String = "createdAt,desc"
    ): Response<ApiResponse<PagedResponse<Quotation>>>
    
    @GET("api/v1/quotations/{id}")
    suspend fun getQuotationById(@Path("id") id: String): Response<ApiResponse<Quotation>>
    
    @POST("api/v1/quotations")
    suspend fun createQuotation(@Body request: CreateQuotationRequest): Response<ApiResponse<Quotation>>
    
    @PUT("api/v1/quotations/{id}")
    suspend fun updateQuotation(
        @Path("id") id: String,
        @Body request: UpdateQuotationRequest
    ): Response<ApiResponse<Quotation>>
    
    @DELETE("api/v1/quotations/{id}")
    suspend fun deleteQuotation(@Path("id") id: String): Response<ApiResponse<Unit>>
}

interface EWayBillApiService {
    @GET("api/v1/eway-bills")
    suspend fun getEWayBills(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String = "createdAt,desc"
    ): Response<ApiResponse<PagedResponse<EWayBill>>>
    
    @GET("api/v1/eway-bills/{id}")
    suspend fun getEWayBillById(@Path("id") id: String): Response<ApiResponse<EWayBill>>
    
    @POST("api/v1/eway-bills")
    suspend fun createEWayBill(@Body request: CreateEWayBillRequest): Response<ApiResponse<EWayBill>>
    
    @DELETE("api/v1/eway-bills/{id}")
    suspend fun deleteEWayBill(@Path("id") id: String): Response<ApiResponse<Unit>>
}

interface PaymentApiService {
    @GET("api/v1/payments")
    suspend fun getPayments(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String = "createdAt,desc"
    ): Response<ApiResponse<PagedResponse<Payment>>>
    
    @GET("api/v1/payments/{id}")
    suspend fun getPaymentById(@Path("id") id: String): Response<ApiResponse<Payment>>
    
    @POST("api/v1/payments")
    suspend fun createPayment(@Body request: CreatePaymentRequest): Response<ApiResponse<Payment>>
    
    @DELETE("api/v1/payments/{id}")
    suspend fun deletePayment(@Path("id") id: String): Response<ApiResponse<Unit>>
}

