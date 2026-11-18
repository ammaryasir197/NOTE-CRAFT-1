package newwork

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import java.time.Instant.now

@Preview(showBackground = true , showSystemUi = true)
@Composable
 fun Scaffoldz () {
     val navController = rememberNavController()
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity
    var lastpresstime by  remember{ mutableLongStateOf(0L) }
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentroute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {Bottombarz(navController)}
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { Home(navController) }
            composable("search") { search(navController) }
            composable("notification") { Notificationz(navController) }
            composable("profile") { Profilez(navController) }
        }

        BackHandler(enabled = currentroute == "home") {
            val currenttime = System.currentTimeMillis()
            if(currenttime - lastpresstime <2000){
                activity?.finish()
            }else {
                lastpresstime = currenttime
                Toast.makeText(context,"Again press to back", Toast.LENGTH_SHORT
                ).show()

            }

        }

    }

}