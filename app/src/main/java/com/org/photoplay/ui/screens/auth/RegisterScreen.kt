package com.org.photoplay.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.org.photoplay.R
import com.org.photoplay.ui.components.AuthTextField
import com.org.photoplay.ui.theme.Black
import com.org.photoplay.ui.theme.Yellow

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxSize(), color = Black) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .size(131.dp)
                    .padding(bottom = 20.dp)
            )
            AuthTextField(text = "FIRST NAME", placeHolder = "First name here") {

            }
            AuthTextField(text = "LAST NAME", placeHolder = "Last name here") { }
            AuthTextField(text = "EMAIL", placeHolder = "Email here") { }
            AuthTextField(text = "PASSWORD", placeHolder = "Password here") { }
            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(44.dp),
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow
                )
            ) {
                Text("REGISTER", color = Black)
            }
        }
    }
}

@Preview
@Composable
private fun RegisterPrev() {
    RegisterScreen()
}