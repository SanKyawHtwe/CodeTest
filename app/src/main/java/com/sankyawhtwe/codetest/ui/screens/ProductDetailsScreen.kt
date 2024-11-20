package com.sankyawhtwe.codetest.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import coil3.compose.AsyncImage
import com.sankyawhtwe.codetest.R
import com.sankyawhtwe.codetest.domain.model.ProductModel
import com.sankyawhtwe.codetest.ui.theme.CodeTestTheme
import com.sankyawhtwe.codetest.ui.viewmodel.ProductDetailsUiState
import com.sankyawhtwe.codetest.ui.viewmodel.ProductDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Serializable
data class ProductDetailsRoute(val id: Int)

fun NavGraphBuilder.productDetailsScreen(
    onNavigateUp: () -> Unit
) {
    composable<ProductDetailsRoute> { backStackEntry ->
        val productDetailsRoute: ProductDetailsRoute = backStackEntry.toRoute()
        val viewModel: ProductDetailsViewModel = koinViewModel {
            parametersOf(productDetailsRoute.id)
        }
        val productDetailsUiState = viewModel.productDetailsUiState.collectAsStateWithLifecycle()
        ProductDetailsScreen(
            productDetailsUiState = productDetailsUiState.value,
            onNavigateUp = onNavigateUp
        )
    }

}

fun NavController.navigateToDetailsScreen(id: Int) {
    navigate(ProductDetailsRoute(id))
}

@Composable
fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    productDetailsUiState: ProductDetailsUiState,
    onNavigateUp: () -> Unit
) {
    val scrollState = rememberScrollState()
    Scaffold(modifier = modifier) { contentPadding ->
        if (productDetailsUiState is ProductDetailsUiState.Success) {
            val productDetails = productDetailsUiState.productDetails
            Box(modifier = Modifier.padding(contentPadding)) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(scrollState),
                ) {
                    IconButton(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(Alignment.Start),
                        onClick = {
                            onNavigateUp()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .background(color = MaterialTheme.colorScheme.primaryContainer)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(400.dp),
                            model = productDetails.image,
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )

                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(RoundedCornerShape(16.dp))
                                .padding(16.dp)
                                .align(Alignment.TopEnd)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                        ) {
                            Text(
                                text = "$ ${productDetails.price}",
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = productDetails.title,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = productDetails.category,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = productDetails.description
                    )
                }
            }
        }
        if (productDetailsUiState is ProductDetailsUiState.Loading)
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
}

@Preview
@Composable
private fun DetailsPreview() {
    CodeTestTheme {
        ProductDetailsScreen(
            productDetailsUiState = ProductDetailsUiState.Success(
                productDetails = ProductModel(
                    id = 1,
                    title = "Apple",
                    price = 10.01,
                    category = "Fruit",
                    description = "this is apple",
                    image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
                )
            ),
            onNavigateUp = {}
        )
    }
}