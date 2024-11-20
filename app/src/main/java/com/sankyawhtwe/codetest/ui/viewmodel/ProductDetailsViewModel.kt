package com.sankyawhtwe.codetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sankyawhtwe.codetest.data.repository.ProductRepository
import com.sankyawhtwe.codetest.domain.model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: Int,
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productDetailsUiState: MutableStateFlow<ProductDetailsUiState> =
        MutableStateFlow(ProductDetailsUiState.Loading)

    var productDetailsUiState: StateFlow<ProductDetailsUiState> = _productDetailsUiState

    private fun getProductDetails() {
        viewModelScope.launch {
            _productDetailsUiState.value = ProductDetailsUiState.Loading
            productRepository.getProductDetails(id = productId).fold(
                {
                    _productDetailsUiState.value = ProductDetailsUiState.Success(it)
                },
                {
                    _productDetailsUiState.value =
                        ProductDetailsUiState.Error(it.message ?: "Something went wrong")

                }
            )
        }
    }

    init {
        getProductDetails()
    }
}

    sealed class ProductDetailsUiState {
        data object Loading : ProductDetailsUiState()
        data class Success(val productDetails: ProductModel) : ProductDetailsUiState()
        data class Error(val message: String) : ProductDetailsUiState()
    }
