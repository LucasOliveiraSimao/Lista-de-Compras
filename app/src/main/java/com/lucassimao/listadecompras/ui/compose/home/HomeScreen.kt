package com.lucassimao.listadecompras.ui.compose.home

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lucassimao.listadecompras.R
import com.lucassimao.listadecompras.ui.PurchaseViewModel
import com.lucassimao.listadecompras.ui.compose.navigation.Routes.INSERT
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    purchaseViewModel: PurchaseViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val purchases = purchaseViewModel.getAllPurchase.collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = {
            HomeContent(modifier, purchases, purchaseViewModel, scope, snackbarHostState, navController)
        },
        floatingActionButton = {
            AddItemButton(navController, modifier)
        }
    )
}

@Composable
private fun AddItemButton(navController: NavHostController, modifier: Modifier) {
    FloatingActionButton(
        onClick = {
            navController.navigate(INSERT)
        },
        modifier = modifier.testTag(stringResource(R.string.tag_test_button_add))
    ) {
        Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_new_purchase))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val navController: NavHostController = rememberNavController()
    HomeScreen(navController)
}

