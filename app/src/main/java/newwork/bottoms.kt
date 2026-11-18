package newwork

import android.R.attr.icon
import android.R.id.icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
 fun Bottombarz(navController: NavController) {
     var selectedindex by remember{ mutableStateOf(0) }

     var iconslist = listOf(
         BottomItem("Home", Icons.Default.Home, "home"),
         BottomItem("Search", Icons.Default.Search, "search"),
         BottomItem("Notifications", Icons.Default.Notifications, "notification"),
         BottomItem("Profile", Icons.Default.Person, "profile")
     )
    val backStackEntry by navController.currentBackStackEntryAsState()
    var currentroute = backStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ){

        iconslist.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = currentroute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo("home") { saveState =true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
                }

            )






        }
    }














}
data class BottomItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)