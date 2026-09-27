package com.example.modul4.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun CounterScreen(modifier: Modifier, number: Int,
                  label: String, onButtonClick: () -> Unit) {
    Column(modifier) {
        Text(text = "$number", fontSize = 72.sp)
        Button(onClick = onButtonClick) {
            Text(text = "$label")
        }
    }
}
//@Composable
//fun CounterScreen(modifier: Modifier) {
//    var number by remember { mutableStateOf(0) }
//    val label by remember { mutableStateOf("Increment2") }
//    Column(modifier) {
//        Text(text = "$number", fontSize = 72.sp)
//        Button(onClick = { number+=2 }) {
//            Text(text = "$label")
//        }
//    }
//}