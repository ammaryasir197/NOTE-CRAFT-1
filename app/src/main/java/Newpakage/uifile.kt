package Newpakage

import android.R.attr.text
import android.content.Context
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import kotlin.getValue
import coil.compose.AsyncImage


@Composable
 fun RomanEmpire (context : Context) {
     var username by remember { mutableStateOf("") }
     var passwrd by remember { mutableStateOf("") }
     var show by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            placeholder = {Text("Enter Username", fontWeight = FontWeight.Bold)},
            singleLine = true ,
            modifier = Modifier.fillMaxWidth()
                .border(2.dp , Color.Black , CircleShape),
            shape = CircleShape
        )
        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            value = passwrd,
            onValueChange = {passwrd = it},
            placeholder = {Text("Enter password", fontWeight = FontWeight.Bold)},
            singleLine = true ,
            modifier = Modifier.fillMaxWidth()
                .border(2.dp , Color.Black , CircleShape),
            shape = CircleShape
        )
        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = {
                val sharepref = context.getSharedPreferences("My Pref" ,
                    Context.MODE_PRIVATE)
                sharepref.edit{
                    putString("username" , username)
                    putString("password" , passwrd)
                }



            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text( "save data")
        }

        Button(
            onClick = {
                val sharepref = context.getSharedPreferences("My Pref" ,
                    Context.MODE_PRIVATE)
                val savedusername = sharepref.getString("username" , "No Username")
                val savedpassword = sharepref.getString("password" , "No Password")
                show = "Username: $savedusername\n Password: $savedpassword"


            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text( "show data")
        }

        if (show.isNotEmpty()){
            Text(
            text = show,
                fontWeight = FontWeight.Bold
            )
        }



    }
}


