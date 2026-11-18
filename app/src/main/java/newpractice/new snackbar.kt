package com.example.practice.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.time.Duration

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun Toasst () {
    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()


    Scaffold(
        snackbarHost = {
            SnackbarHost (hostState = snackbarHostState){data ->
                Snackbar(
                    snackbarData = data,
                    shape = RoundedCornerShape(16.dp),        // corners round karne ke liye
                    containerColor = Color(0xFF6650a4),       // background color
                    contentColor = Color.White,                // text/action color
                    actionColor = Color.Yellow                 // "Hide" button ka color


                )
            }
        }
    ) { innerPaddding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPaddding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "This is a snackbar",
                            actionLabel = "Hide",
                            duration = SnackbarDuration.Long
                        )

                    }
                },
                modifier = Modifier.width(320.dp),
                shape = RoundedCornerShape(16.dp)

            ) {
                Text(
                    text = "Toast",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }

    }

}