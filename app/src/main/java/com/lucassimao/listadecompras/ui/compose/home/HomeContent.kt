package com.lucassimao.listadecompras.ui.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.utils.calculateTotalPurchase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeContent(
    modifier: Modifier,
    purchases: State<List<PurchaseModel>>,
    viewModel: PurchaseViewModel,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TotalPurchaseAndDeleteButton(modifier, purchases, viewModel)
        PurchaseList(modifier, purchases, scope, viewModel, snackbarHostState)

    }
}


@Composable
private fun TotalPurchaseAndDeleteButton(
    modifier: Modifier,
    purchases: State<List<PurchaseModel>>,
    viewModel: PurchaseViewModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .testTag(stringResource(R.string.test_tag_text_total)),
            text = stringResource(
                R.string.display_total_purchase_value,
                calculateTotalPurchase(purchases.value)
            ),
            color = Color.White,
            fontSize = 20.sp
        )
        OutlinedButton(
            modifier = modifier
                .weight(1f)
                .testTag(stringResource(R.string.tag_test_button_delete)),
            onClick = {
                viewModel.deleteAllPurchase()
            }) {
            Text(
                text = stringResource(R.string.delete_everything),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun PurchaseList(
    modifier: Modifier,
    purchases: State<List<PurchaseModel>>,
    scope: CoroutineScope,
    viewModel: PurchaseViewModel,
    snackbarHostState: SnackbarHostState
) {
    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .testTag(stringResource(R.string.tag_test_list))
    ) {
        items(items = purchases.value, key = { it.item_id }) { item ->

            val state = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToStart) {
                        scope.launch {
                            viewModel.delete(item)
                            snackbarHostState.showSnackbar(
                                message = "Item excluído com sucesso",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    true
                }
            )

            SwipeItem(state, item)
        }
    }
}