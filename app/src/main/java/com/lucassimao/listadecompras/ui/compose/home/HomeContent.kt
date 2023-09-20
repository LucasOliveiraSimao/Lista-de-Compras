package com.lucassimao.listadecompras.ui.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucassimao.listadecompras.R

@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total: R$ 100,00", modifier = modifier.align(Alignment.CenterVertically))
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.delete_everything))
            }
        }

        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items((1..100).toList()) {
                ItemList()
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    HomeContent()
}