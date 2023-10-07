package com.lucassimao.listadecompras.ui.compose.update

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.lucassimao.listadecompras.data.model.PurchaseModel
import com.lucassimao.listadecompras.ui.compose.BaseContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UpdateScreen(navController: NavHostController, purchase: PurchaseModel?) {
    Scaffold {
        BaseContent(navController = navController, purchase)
    }
}