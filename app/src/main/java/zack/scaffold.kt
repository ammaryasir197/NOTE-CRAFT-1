package zack

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true , showSystemUi = true)
@Composable
 fun  navigations() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val snackbarHostState = remember { SnackbarHostState() }
    var welcomeShown by rememberSaveable { mutableStateOf(false) }
    BackHandler(enabled = currentRoute != "home" && currentRoute != "fiel") {
        navController.navigate("home/{username}") {
            popUpTo("home") { inclusive = true }
        }
    }


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState){ data ->
            Snackbar(
                snackbarData = data,
                containerColor = Color.Black,
                contentColor = Color.White,
                actionColor = Color.Yellow,
                shape = RoundedCornerShape(10.dp)
            )
        }
        },
        bottomBar  = {
            if (currentRoute != "fiel") {
                Bottombarpractice(navController = navController)

            }


            }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "fiel",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("fiel"){
                fields (navController)
            }
            composable("home/{username}"){  backStackEntry ->
                val  username = backStackEntry.arguments?.getString("username")?: ""
                hmm(navController = navController,
                    snackbarHostState = snackbarHostState,
                    username = username,
                    welcomeShown = welcomeShown,
                    onwelcomeShown = {welcomeShown = true})

            }
            composable("search"){
                sear (navController)
            }
            composable("notify"){
                not (navController)
            }
            composable("profile"){
                ppr (navController)
            }

        }


    }


}