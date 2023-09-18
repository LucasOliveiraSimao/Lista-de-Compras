package com.lucassimao.listadecompras.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lucassimao.listadecompras.ui.compose.navigation.SetupNavigation
import com.lucassimao.listadecompras.ui.ui.theme.ListaDeComprasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListaDeComprasTheme {
                val navController: NavHostController = rememberNavController()
                SetupNavigation(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListaDeComprasTheme {

    }
}