package com.lucassimao.listadecompras.ui.compose.insert

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.ui.compose.BaseContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.add_item_to_cart),
                    fontSize = 24.sp,
                    modifier = Modifier.padding(8.dp)
                )
                BaseContent(navController, null)
            }
        }
    )
}

@Preview
@Composable
fun PreviewScreen() {
    InsertScreen()
}