package com.invoicegenerator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invoicegenerator.data.model.CreateInvoiceRequest
import com.invoicegenerator.data.model.Invoice
import com.invoicegenerator.data.repository.InvoiceRepository
import com.invoicegenerator.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class InvoiceListState(
    val isLoading: Boolean = false,
    val invoices: List<Invoice> = emptyList(),
    val error: String? = null,
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val hasMore: Boolean = true
)

data class InvoiceCreateState(
    val isLoading: Boolean = false,
    val createdInvoice: Invoice? = null,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val invoiceRepository: InvoiceRepository
) : ViewModel() {
    
    private val _listState = MutableStateFlow(InvoiceListState())
    val listState: StateFlow<InvoiceListState> = _listState.asStateFlow()
    
    private val _createState = MutableStateFlow(InvoiceCreateState())
    val createState: StateFlow<InvoiceCreateState> = _createState.asStateFlow()
    
    fun loadInvoices(page: Int = 0) {
        viewModelScope.launch {
            invoiceRepository.getInvoices(page).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _listState.value = _listState.value.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        val pagedResponse = result.data
                        _listState.value = InvoiceListState(
                            isLoading = false,
                            invoices = if (page == 0) pagedResponse.content 
                                      else _listState.value.invoices + pagedResponse.content,
                            currentPage = pagedResponse.pageable.pageNumber,
                            totalPages = pagedResponse.totalPages,
                            hasMore = !pagedResponse.last
                        )
                    }
                    is Result.Error -> {
                        _listState.value = _listState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun createInvoice(
        clientName: String,
        amount: Double,
        dateIssued: String,
        dueDate: String
    ) {
        viewModelScope.launch {
            val request = CreateInvoiceRequest(
                clientName = clientName,
                amount = amount,
                dateIssued = dateIssued,
                dueDate = dueDate
            )
            
            invoiceRepository.createInvoice(request).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _createState.value = InvoiceCreateState(isLoading = true)
                    }
                    is Result.Success -> {
                        _createState.value = InvoiceCreateState(
                            isLoading = false,
                            createdInvoice = result.data,
                            isSuccess = true
                        )
                        // Refresh the list
                        loadInvoices(0)
                    }
                    is Result.Error -> {
                        _createState.value = InvoiceCreateState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun updateInvoiceStatus(id: String, status: String) {
        viewModelScope.launch {
            invoiceRepository.updateInvoice(id, status).collect { result ->
                when (result) {
                    is Result.Success -> {
                        // Refresh the list
                        loadInvoices(0)
                    }
                    is Result.Error -> {
                        _listState.value = _listState.value.copy(error = result.message)
                    }
                    else -> {}
                }
            }
        }
    }
    
    fun deleteInvoice(id: String) {
        viewModelScope.launch {
            invoiceRepository.deleteInvoice(id).collect { result ->
                when (result) {
                    is Result.Success -> {
                        // Refresh the list
                        loadInvoices(0)
                    }
                    is Result.Error -> {
                        _listState.value = _listState.value.copy(error = result.message)
                    }
                    else -> {}
                }
            }
        }
    }
    
    fun clearCreateState() {
        _createState.value = InvoiceCreateState()
    }
    
    fun clearError() {
        _listState.value = _listState.value.copy(error = null)
    }
}

