package com.sankyawhtwe.codetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sankyawhtwe.codetest.data.repository.ProductRepository
import com.sankyawhtwe.codetest.domain.model.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.KoinApplication.Companion.init

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {


    private val _productUiState: MutableStateFlow<ProductUiState> = MutableStateFlow(
        ProductUiState.Loading
    )
    var productUiState: StateFlow<ProductUiState> = _productUiState

    private val _categoryUiState: MutableStateFlow<CategoryUiState> = MutableStateFlow(
        CategoryUiState.Loading
    )

    var categoryUiState: StateFlow<CategoryUiState> = _categoryUiState

    fun getProductList() {
        viewModelScope.launch {
            _productUiState.value = ProductUiState.Loading
            productRepository.getProductList().fold(
                {
                    _productUiState.value = ProductUiState.Success(it)
                },
                {
                    _productUiState.value =
                        ProductUiState.Error(it.message ?: "Something went wrong")
                }
            )
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            _categoryUiState.value = CategoryUiState.Loading
            productRepository.getCategories().fold(
                {
                    _categoryUiState.value = CategoryUiState.Success(it)
                },
                {
                    _categoryUiState.value =
                        CategoryUiState.Error(it.message ?: "Something went wrong")
                }
            )
        }
    }

    fun getProductsByCategory(category: String) {
        viewModelScope.launch {
            _productUiState.value = ProductUiState.Loading
            productRepository.getProductsByCategory(category = category).fold(
                {
                    _productUiState.value = ProductUiState.Success(it)
                },
                {
                    _productUiState.value =
                        ProductUiState.Error(it.message ?: "Something went wrong")
                }
            )
        }
    }

    init {
        getProductList()
        getCategories()
    }
}

sealed class ProductUiState {
    data object Loading : ProductUiState()
    data class Success(val products: List<ProductModel>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}

sealed class CategoryUiState {
    data object Loading : CategoryUiState()
    data class Success(val categories: List<String>) : CategoryUiState()
    data class Error(val message: String) : CategoryUiState()
}