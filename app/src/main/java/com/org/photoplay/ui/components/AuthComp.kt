package com.org.photoplay.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthTextField(
    modifier: Modifier = Modifier,
    text: String,
    placeHolder: String,
    onValueChanged: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }
    Column(modifier = modifier) {
        Text(text, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        TextField(modifier = Modifier, value = value, onValueChange = {
            value = it
            onValueChanged(it)
        }, placeholder = {
            Text(placeHolder, color = Color.Gray)
        })
    }
}