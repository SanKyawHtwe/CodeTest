package com.sankyawhtwe.codetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sankyawhtwe.codetest.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateProductViewModel(
    private val productRepository: ProductRepository,
) : ViewModel() {

    private val _createProductUiState: MutableStateFlow<CreateProductUiState> =
        MutableStateFlow(
            CreateProductUiState.Loading
        )

    var createProductUiState: StateFlow<CreateProductUiState> = _createProductUiState

    private val _categoryUiState: MutableStateFlow<CategoryUiState> = MutableStateFlow(
        CategoryUiState.Loading
    )

    var categoryUiState: StateFlow<CategoryUiState> = _categoryUiState

    fun createProduct() {
        viewModelScope.launch {
            _createProductUiState.value = CreateProductUiState.Loading
            productRepository.createProduct().fold(
                {
                    _createProductUiState.value = CreateProductUiState.Success
                },
                {
                    _createProductUiState.value =
                        CreateProductUiState.Error(it.message ?: "Something went wrong")
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

    init {
        getCategories()
    }
}

sealed class CreateProductUiState {
    data object Loading : CreateProductUiState()
    data object Success : CreateProductUiState()
    data class Error(val message: String) : CreateProductUiState()
}