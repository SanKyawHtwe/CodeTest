package com.sankyawhtwe.codetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sankyawhtwe.codetest.data.repository.ProductRepository
import com.sankyawhtwe.codetest.domain.model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {



    private val _productUiState: MutableStateFlow<ProductUiState> = MutableStateFlow(
        ProductUiState.Loading
    )
    var productUiState: StateFlow<ProductUiState> = _productUiState

    private fun getProductList() {
        viewModelScope.launch {
            _productUiState.value = ProductUiState.Loading
            productRepository.getProductList().fold(
                {
                    _productUiState.value = ProductUiState.Success(it)
                },
                {
                    _productUiState.value = ProductUiState.Error(it.message ?: "Something went wrong")
                }
            )
        }
    }
    init {
        getProductList()
    }
}

sealed class ProductUiState {
    data object Loading : ProductUiState()
    data class Success(val products: List<ProductModel>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}