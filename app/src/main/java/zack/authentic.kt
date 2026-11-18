package zack

import android.net.Uri
import android.util.Patterns
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController


@Composable
 fun fields (navController : NavController) {
     var Emails by remember { mutableStateOf("") }
     var Username by remember { mutableStateOf("") }
    var isEmailvalid = Patterns.EMAIL_ADDRESS.matcher(Emails).matches()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        OutlinedTextField(
            value = Username,
            onValueChange = {Username = it},
            placeholder = {Text("Enter Username",
                fontWeight = FontWeight.Bold,
                color = Color.Black)},
            singleLine = true,
            modifier = Modifier.border(2.dp, Color.Black,
                RoundedCornerShape(50.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )



        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))














        OutlinedTextField(
            value = Emails,
            onValueChange = { Emails = it },
            modifier = Modifier.border(2.dp ,
                color = Color.Black,
                RoundedCornerShape(50.dp)),
            placeholder = {
                Text("Enter Your Email",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
            },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )


        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        Button(
            onClick = {navController.navigate("home/${Uri.encode(Username)}"){
                popUpTo("fiel") { inclusive = true }
            } },
            modifier = Modifier.width(190.dp),
            enabled = isEmailvalid
        ) {
           Text("Log in")

        }
    }

}