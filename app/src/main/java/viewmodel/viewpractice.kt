package viewmodel

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true , showSystemUi = true)
@Composable
 fun viewz () {
     val vm : Application = viewModel()


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
        {
        OutlinedTextField(
            value = vm.email ,
            onValueChange = { vm.updateEmail(it) },
            placeholder = { Text("Enter your Email",
                fontWeight = FontWeight.Bold) },
            singleLine = true,
            modifier = Modifier.border(2.dp , Color.Black ,
                RoundedCornerShape(30.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
            Spacer(modifier = Modifier.height(20.dp))


            OutlinedTextField(
                value = vm.password,
                onValueChange = { vm.updatePassword(it) },
                placeholder = { Text("Enter your Password",
                    fontWeight = FontWeight.Bold) },
                singleLine = true,
                modifier = Modifier.border(2.dp , Color.Black ,
                    RoundedCornerShape(30.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )

            ) {
                Text("Login Here")
            }







    }




}