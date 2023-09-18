package com.lucassimao.listadecompras.ui.compose.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemList(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(BorderStroke(2.dp, Color.White)),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.padding(2.dp)
        ) {
            Text(
                text = "Arroz",
                modifier = modifier
                    .weight(1f)
                    .padding(start = 6.dp),
                color = Color.Black
            )
            Text(
                text = "2 x",
                modifier = modifier
                    .weight(1f)
                    .padding(end = 16.dp),
                textAlign = TextAlign.End,
                color = Color.Black
            )
            Text(
                text = "R$ 2,00",
                modifier = modifier.weight(1f),
                color = Color.Black
            )
            Icon(
                Icons.Filled.MoreVert,
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemList() {
    ItemList()
}