package com.lucassimao.listadecompras.ui.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.utils.calculateTotalPurchase
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    viewModel: PurchaseViewModel = koinViewModel()
) {

    val purchases = viewModel.getAllPurchase.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
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
                    .testTag("Total"),
                text = "Total: R$ ${calculateTotalPurchase(purchases.value)}",
                color = Color.White,
                fontSize = 20.sp
            )
            OutlinedButton(
                modifier = modifier
                    .weight(1f)
                    .testTag("Delete"),
                onClick = {
                    viewModel.deleteAllPurchase()
                }) {
                Text(
                    text = stringResource(id = R.string.delete_everything),
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }

        LazyColumn(
            modifier = modifier
                .padding(8.dp)
                .testTag("List")
        ) {
            items(purchases.value) {
                ItemList(it)
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    HomeContent()
}