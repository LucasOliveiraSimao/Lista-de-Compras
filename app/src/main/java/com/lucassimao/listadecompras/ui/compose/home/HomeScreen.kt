package com.lucassimao.listadecompras.ui.compose.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lucassimao.listadecompras.utils.Routes.INSERT

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        topBar = {
            Toolbar()
        },
        content = {
            HomeContent()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(INSERT)
            }, modifier = Modifier.testTag("Fab")) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController: NavHostController = rememberNavController()
    HomeScreen(navController)
}

