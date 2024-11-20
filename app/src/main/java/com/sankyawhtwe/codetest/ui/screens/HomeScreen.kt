package com.sankyawhtwe.codetest.ui.screens

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import coil3.compose.AsyncImage
import com.sankyawhtwe.codetest.R
import com.sankyawhtwe.codetest.domain.model.ProductModel
import com.sankyawhtwe.codetest.ui.theme.CodeTestTheme
import com.sankyawhtwe.codetest.ui.viewmodel.CategoryUiState
import com.sankyawhtwe.codetest.ui.viewmodel.ProductUiState
import com.sankyawhtwe.codetest.ui.viewmodel.ProductViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
data object HomeRoute

fun NavController.navigateToHomeScreen(
    navOptions: NavOptions? = null
) {
    navigate(HomeRoute, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen(
    onProductClick: (Int) -> Unit
) {
    composable<HomeRoute> {
        val viewModel: ProductViewModel = koinViewModel()
        val productUiState = viewModel.productUiState.collectAsStateWithLifecycle()
        val categoryUiState = viewModel.categoryUiState.collectAsStateWithLifecycle()
        HomeScreen(
            productUiState = productUiState.value,
            categoryUiState = categoryUiState.value,
            onProductClick = onProductClick
        )
    }
}


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    productUiState: ProductUiState,
    categoryUiState: CategoryUiState,
    viewModel: ProductViewModel = koinViewModel(),
    onProductClick: (Int) -> Unit
) {


    Scaffold(
        modifier = modifier,
    ) { contentPadding ->


        val orientation = LocalConfiguration.current.orientation
        var expanded by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Icon(
                            modifier = Modifier,
                            painter = painterResource(R.drawable.ic_filter),
                            contentDescription = "Filter",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier,
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        if (categoryUiState is CategoryUiState.Success) {
                            val categoryList = categoryUiState.categories

                            repeat(categoryList.size) { index ->
                                DropdownMenuItem(
                                    text = { Text(categoryList[index]) },
                                    onClick = {
                                        viewModel.getProductsByCategory(categoryList[index])
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                if (productUiState is ProductUiState.Success) {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(12.dp),
                        columns = StaggeredGridCells.Fixed(
                            if (orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 4
                        ),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        val productList = productUiState.products
                        items(
                            count = productList.size,
                            key = { productList[it].id }
                        ) { index ->
                            Product(
                                product = productList[index],
                                onProductClick = onProductClick
                            )
                        }
                    }
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp),
                onClick = {},
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add product"
                )
            }
        }

    }
    if (productUiState is ProductUiState.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.secondary,
                strokeCap = StrokeCap.Butt,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
    if (productUiState is ProductUiState.Error) {
        Toast.makeText(LocalContext.current, "Something went wrong", Toast.LENGTH_SHORT).show()
        Log.d("ErrorMessage", productUiState.message)
    }
}


@Composable
fun Product(
    modifier: Modifier = Modifier,
    product: ProductModel,
    onProductClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                onProductClick(product.id)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp)),
            model = product.image,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = product.title
        )
        Text(
            modifier = Modifier.wrapContentWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = "$ ${product.price}"
        )
        Spacer(modifier = Modifier.size(8.dp))
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CodeTestTheme {
        HomeScreen(
            productUiState = ProductUiState.Success(
                products = listOf(
                    ProductModel(
                        id = 1,
                        title = "Apple",
                        price = 10.01,
                        category = "Fruit",
                        description = "this is apple",
                        image = ""
                    ),
                    ProductModel(
                        id = 2,
                        title = "Orange",
                        price = 10.01,
                        category = "Fruit",
                        description = "this is apple",
                        image = ""
                    ),
                    ProductModel(
                        id = 3,
                        title = "Banana",
                        price = 10.01,
                        category = "Fruit",
                        description = "this is apple",
                        image = ""
                    ),
                    ProductModel(
                        id = 4,
                        title = "Apple",
                        price = 10.01,
                        category = "Fruit",
                        description = "this is apple",
                        image = ""
                    ),
                )
            ),
            categoryUiState = CategoryUiState.Success(
                categories = listOf(
                    "All"
                )
            ),
            onProductClick = {}
        )
    }
}