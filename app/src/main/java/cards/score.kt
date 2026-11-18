package cards


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
 fun scores () {

     var score by rememberSaveable {mutableStateOf(0) }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Score = $score",
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { score++ },
            modifier = Modifier.width(150.dp),

        ) {
            Text("Increase")
        }


        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {score-- }
                    ,
            modifier = Modifier.width(150.dp),
            enabled = score>0
        ) {
            Text("Deccrease")
        }




    }


}