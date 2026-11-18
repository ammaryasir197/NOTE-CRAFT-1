package newwork

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Preview(showBackground = true, showSystemUi = true)
@Composable
 fun  Randomss() {
    var randomNumber by remember { mutableStateOf<Int?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    LaunchedEffect(isLoading) {
        if(isLoading){
            delay(2000)
            randomNumber = (1..1000).random()
            isLoading = false
        }


    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if(isLoading){
            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(16.dp))

            Text("Generatig Number...",
                fontWeight = FontWeight.Bold)
        }else if (randomNumber != null){
            Text("Your number : $randomNumber",
                fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(26.dp))

        Button(
            onClick = {
                isLoading = true 
                randomNumber = null
            },
            enabled = !isLoading
        ) {

            Text("Generte number")
        }



    }

}