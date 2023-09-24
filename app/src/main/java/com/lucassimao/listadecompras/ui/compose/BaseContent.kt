package com.lucassimao.listadecompras.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.utils.CurrencyAmountInputVisualTransformation
import com.lucassimao.listadecompras.utils.removeLastTwoCharacters
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseContent(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: PurchaseViewModel = koinViewModel()
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var productName by remember { mutableStateOf("") }

    var productAmount by remember { mutableStateOf("") }

    var productPrice by remember { mutableStateOf("") }

    val isProductNameEmpty = productName.isBlank()
    val isProductAmountEmpty = productAmount.isBlank()
    val isProductAPriceEmpty = productPrice.isBlank()

    val isAnyFieldEmpty = isProductNameEmpty || isProductAmountEmpty || isProductAPriceEmpty

    val fullWidthModifier = modifier.fillMaxWidth()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Color.White
    ) {
        Column(
            modifier = modifier
                .padding(16.dp)
        ) {

            OutlinedTextField(
                value = productName,
                modifier = fullWidthModifier.testTag("ProductNameField"),
                onValueChange = {
                    productName = it
                },
                label = {
                    Text(text = stringResource(R.string.product_name))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )

            OutlinedTextField(
                value = productAmount,
                modifier = fullWidthModifier.testTag("ProductAmountField"),
                onValueChange = {
                    if (it.length <= 3) {
                        productAmount = it
                    }
                },
                label = {
                    Text(text = stringResource(R.string.product_amount))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = productPrice,
                modifier = fullWidthModifier.testTag("ProductPriceField"),
                onValueChange = {
                    productPrice = if (it.startsWith("0")) {
                        ""
                    } else {
                        it
                    }
                },
                label = {
                    Text(text = stringResource(R.string.product_price))
                },
                visualTransformation = CurrencyAmountInputVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ) {

                OutlinedButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                ) {
                    Text(text = stringResource(R.string.cancel))
                }

                Button(
                    onClick = {
                        val result = productPrice.removeLastTwoCharacters()
                        viewModel.insert(productName, productAmount.toInt(), result)
                        productName = ""
                        productAmount = ""
                        productPrice = ""
                        scope.launch {
                            snackbarHostState.showSnackbar("Item salvo com sucesso")
                        }
                    },
                    modifier = modifier
                        .weight(1f)
                        .padding(8.dp),
                    enabled = !isAnyFieldEmpty
                ) {
                    Text(text = stringResource(R.string.save))
                }
            }

        }
    }


}


@Preview(showBackground = true)
@Composable
fun Preview() {
    val navController: NavHostController = rememberNavController()
    BaseContent(navController)
}