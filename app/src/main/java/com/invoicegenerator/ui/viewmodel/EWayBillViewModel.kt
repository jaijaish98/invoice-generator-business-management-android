package com.invoicegenerator.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.invoicegenerator.data.model.CreateEWayBillRequest
import com.invoicegenerator.data.model.EWayBill
import com.invoicegenerator.data.repository.EWayBillRepository
import com.invoicegenerator.data.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class EWayBillListState(
    val isLoading: Boolean = false,
    val eWayBills: List<EWayBill> = emptyList(),
    val error: String? = null,
    val currentPage: Int = 0,
    val totalPages: Int = 0,
    val hasMore: Boolean = true
)

data class EWayBillCreateState(
    val isLoading: Boolean = false,
    val createdEWayBill: EWayBill? = null,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class EWayBillViewModel @Inject constructor(
    private val eWayBillRepository: EWayBillRepository
) : ViewModel() {
    
    private val _listState = MutableStateFlow(EWayBillListState())
    val listState: StateFlow<EWayBillListState> = _listState.asStateFlow()
    
    private val _createState = MutableStateFlow(EWayBillCreateState())
    val createState: StateFlow<EWayBillCreateState> = _createState.asStateFlow()
    
    fun loadEWayBills(page: Int = 0) {
        viewModelScope.launch {
            eWayBillRepository.getEWayBills(page).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _listState.value = _listState.value.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        val pagedResponse = result.data
                        _listState.value = EWayBillListState(
                            isLoading = false,
                            eWayBills = if (page == 0) pagedResponse.content 
                                      else _listState.value.eWayBills + pagedResponse.content,
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
    
    fun createEWayBill(
        billNumber: String,
        consignorName: String,
        consigneeName: String,
        goodsValue: Double,
        transportMode: String,
        vehicleNumber: String,
        distanceKm: Int,
        validFrom: String,
        validUntil: String
    ) {
        viewModelScope.launch {
            val request = CreateEWayBillRequest(
                billNumber = billNumber,
                consignorName = consignorName,
                consigneeName = consigneeName,
                goodsValue = goodsValue,
                transportMode = transportMode,
                vehicleNumber = vehicleNumber,
                distanceKm = distanceKm,
                validFrom = validFrom,
                validUntil = validUntil
            )
            
            eWayBillRepository.createEWayBill(request).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _createState.value = EWayBillCreateState(isLoading = true)
                    }
                    is Result.Success -> {
                        _createState.value = EWayBillCreateState(
                            isLoading = false,
                            createdEWayBill = result.data,
                            isSuccess = true
                        )
                        // Refresh the list
                        loadEWayBills(0)
                    }
                    is Result.Error -> {
                        _createState.value = EWayBillCreateState(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }
        }
    }
    
    fun deleteEWayBill(id: String) {
        viewModelScope.launch {
            eWayBillRepository.deleteEWayBill(id).collect { result ->
                when (result) {
                    is Result.Success -> {
                        // Refresh the list
                        loadEWayBills(0)
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
        _createState.value = EWayBillCreateState()
    }
    
    fun clearError() {
        _listState.value = _listState.value.copy(error = null)
    }
}

