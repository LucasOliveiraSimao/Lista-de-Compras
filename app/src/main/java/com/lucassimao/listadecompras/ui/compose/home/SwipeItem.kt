package com.lucassimao.listadecompras.ui.compose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lucassimao.listadecompras.data.model.PurchaseModel

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeItem(
    dismissState: DismissState,
    purchase: PurchaseModel
) {
    SwipeToDismiss(
        state = dismissState,
        background = {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(horizontal = 20.dp, vertical = 6.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        },
        dismissContent = {
            ItemList(
                purchase = purchase
            )
        }
    )
}