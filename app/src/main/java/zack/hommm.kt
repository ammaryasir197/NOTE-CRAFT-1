package zack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout


@Composable
 fun hmm (navController : NavController ,
          snackbarHostState: SnackbarHostState,
          username : String,
          welcomeShown : Boolean,
          onwelcomeShown : () -> Unit) {

    DisposableEffect(Unit) {
        onDispose { onwelcomeShown() }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!welcomeShown) {
            Text(
                text = "Welcome $username",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            )
        }

    }
    var jacob by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(" Home screen" ,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Button(
            onClick = {jacob = true},
            modifier = Modifier.width(300.dp)

        ) {
            Text(" Delete item ")
        }

    }
    if (jacob){
        AlertDialog(
            onDismissRequest = {jacob = false},
            title = {
                Text(" Delete item ")
            },
            text = {
                Text(" Do you want to Delete item ")
            },
            confirmButton = {
                TextButton(
                    onClick = {jacob = false
                        scope.launch {
                             snackbarHostState.showSnackbar(
                                message = "  Item Deleted ",
                                actionLabel = "  Undo ",
                                duration = SnackbarDuration.Long




                            )




                        }




                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black

                    )
                ) {
                    Text(" Delete ")
                }



            },
            dismissButton = {
                TextButton(
                    onClick = {jacob = false},
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Black

                    )
                ) {
                    Text(" No ")
                }
            }






        )






    }


    
}