package Newpakage

import android.R
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview( showBackground = true , showSystemUi = true )
@Composable
 fun shamt () {
     var password by remember {  mutableStateOf("")}
     var passwordvisible by remember {  mutableStateOf(false)}

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            placeholder = { Text("Enter Password",
                fontWeight = FontWeight.Bold)},
            singleLine = true,
            modifier = Modifier.border(2.dp, Color.Black,
                RoundedCornerShape(30.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            visualTransformation = if (passwordvisible){
                VisualTransformation.None
            }else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordvisible = !passwordvisible
                    }
                ) {
                    Icon(
                        imageVector = if (passwordvisible){
                            Icons.Filled.VisibilityOff
                        }else{
                            Icons.Filled.Visibility
                        },
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )




    }

}
