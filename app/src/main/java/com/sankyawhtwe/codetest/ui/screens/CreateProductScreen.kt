package com.sankyawhtwe.codetest.ui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.G
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sankyawhtwe.codetest.ui.theme.CodeTestTheme
import com.sankyawhtwe.codetest.ui.viewmodel.CategoryUiState
import com.sankyawhtwe.codetest.ui.viewmodel.CreateProductUiState
import com.sankyawhtwe.codetest.ui.viewmodel.CreateProductViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object CreateProductRoute

fun NavGraphBuilder.createProductScreen(
) {
    composable<CreateProductRoute> {
        val viewModel: CreateProductViewModel = koinViewModel()
        val createProductUiState = viewModel.createProductUiState.collectAsStateWithLifecycle()
        val categoryUiState = viewModel.categoryUiState.collectAsStateWithLifecycle()

        CreateProductScreen(
            createProductUiState = createProductUiState.value,
            categoryUiState = categoryUiState.value,
            onProductCreate = { viewModel.createProduct() }
        )

    }
}

fun NavController.navigateToCreateProductScreen() {
    navigate(CreateProductRoute)
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateProductScreen(
    modifier: Modifier = Modifier,
    createProductUiState: CreateProductUiState,
    categoryUiState: CategoryUiState,
    onProductCreate: () -> Unit
) {
    Scaffold() { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            var title by rememberSaveable { mutableStateOf("") }
            var price by rememberSaveable { mutableStateOf("") }
            var description by rememberSaveable { mutableStateOf("") }
            var imageLink by rememberSaveable { mutableStateOf("") }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    maxLines = 1,
                    label = { Text("Product title") },
                    onValueChange = {
                        title = it
                    },
                )
                Spacer(modifier = Modifier.size(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = price,
                    maxLines = 1,
                    singleLine = true,
                    label = { Text("Product price") },
                    onValueChange = {
                        price = it
                    },
                )
                Spacer(modifier = Modifier.size(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = description,
                    maxLines = 3,
                    label = { Text("Product description") },
                    onValueChange = {
                        description = it
                    },
                )
                Spacer(modifier = Modifier.size(12.dp))

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = imageLink,
                    maxLines = 1,
                    label = { Text("Product image link") },
                    onValueChange = {
                        imageLink = it
                    },
                )
                Spacer(modifier = Modifier.size(12.dp))

                if (categoryUiState is CategoryUiState.Success) {
                    val categoryList = categoryUiState.categories
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf(categoryList[0]) }

                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .fillMaxWidth(),
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {
                        TextField(
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            readOnly = true,
                            value = selectedOptionText,
                            onValueChange = { },
                            label = { Text("Categories") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expanded
                                )
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            }
                        ) {
                            categoryList.forEach { categories ->
                                DropdownMenuItem(
                                    onClick = {
                                        selectedOptionText = categories
                                        expanded = false
                                    },
                                    text = { Text(categories) }
                                )

                            }
                        }
                    }
                }

            }
            var isSubmitting by remember { mutableStateOf(false) }
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                onClick = {
                    if (
                        title.isNotEmpty()
                        && price.isNotEmpty()
                        && description.isNotEmpty()
                        && imageLink.isNotEmpty()
                    ) {
                        isSubmitting = true
                        onProductCreate()
                        isSubmitting = false
                    }
                },
                enabled = !isSubmitting
            ) {
                Text(if (isSubmitting) "Submitting..." else "Create Product")
            }
        }
    }
}


//@Preview
//@Composable
//private fun CreateProductPreview() {
//    CodeTestTheme {
//        CreateProductScreen()
//    }
//}