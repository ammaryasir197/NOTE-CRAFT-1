package zack

import android.R.attr.icon
import android.R.id.icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
 fun Bottombarpractice (navController : NavController ){
     val items = listOf(
         BottomNavItem(Icons.Default.Home, route ="home" ),
         BottomNavItem(Icons.Default.Search, route ="search" ),
         BottomNavItem(Icons.Default.Notifications, route ="notify"  ),
         BottomNavItem(Icons.Default.Person, route = "profile" )


     )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    Row(

        modifier = Modifier.fillMaxWidth()
            .background(color = Color.Gray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically

    ) {

        NavigationBar {
            items.forEach { item ->
                 NavigationBarItem(
                     selected = currentRoute == item.route ,
                     onClick = { navController.navigate(item.route) {
                         popUpTo("home/{username}") { saveState = true }
                         launchSingleTop = true
                         restoreState = true
                     } },

                     icon = {
                         val isSelected = currentRoute == item.route

                             Icon(
                                 imageVector = item.icon,
                                 contentDescription = item.route,
                                 tint = if (isSelected ) Color.Blue else Color.Black

                             )

                     },

                             colors = NavigationBarItemDefaults.colors(
                             indicatorColor = Color.Transparent
                             )







                 )








            }




        }

        
        
        

    }








 }

data class BottomNavItem(
    val icon: ImageVector, // ya ImageVector
    val route: String
)
