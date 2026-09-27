package com.example.modul4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modul4.screens.CounterScreen
import com.example.modul4.screens.TicketOrderScreen
import com.example.modul4.ui.theme.Modul4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul4Theme {
                TicketOrderScreen()
//                val snackbarHostState = remember { SnackbarHostState() }
//                var number by rememberSaveable() { mutableStateOf(1) }
//
//                Scaffold(
//                    modifier = Modifier.fillMaxSize(),
//                    snackbarHost = {
//                        SnackbarHost(
//                            hostState = snackbarHostState
//                        )
//                    }
//                ) { innerPadding ->
//                    Column() {
//                        CounterScreen(Modifier.padding(innerPadding),
//                            number = number,
//                            label = "Double",
//                            onButtonClick = { number*=2 }
//                        )
//
//                        LaunchedEffect(number) {
//                            snackbarHostState.showSnackbar(
//                                "Number $number is shown!"
//                            )
//                        }
//                        LaunchedEffect(Unit) {
//                            number = Repo.getData()
//                        }
//                    }
//                }
            }
        }
    }
}
