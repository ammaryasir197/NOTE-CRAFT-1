import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true , showSystemUi = true)
@Composable
fun tables () {

    var digit by remember { mutableStateOf("") }
    var showTable by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(start = 50.dp , top = 60.dp)


    ) {
        TextField(
            value = digit,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    digit = newValue
                    showTable = false
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Enter Number",
                fontWeight = FontWeight.Bold
            ) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold
            ),

        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))

       Button(
            onClick = {
              showTable = true
            },
            modifier = Modifier.width(300.dp)
        ) {
            Text("Show Me ")
        }

        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        if(showTable){
            val number = digit.toIntOrNull()?:0
            Column {
                for (i in 1..10) {
                    Text(
                        text = "$number x $i = ${number * i}",
                        fontWeight = FontWeight.Bold
                    )
                }
            }





        }









    }



}