package newpractice

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.updateLiveLiteralValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview( showBackground = true , showSystemUi = true)
@Composable
 fun barr () {
     val items = listOf(
         Icons.Default. Home,
         Icons.Default. Search,
         Icons.Default. Notifications,
         Icons.Default. Person
     )
    var selected by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(70.dp)
            .background(color = Color.LightGray),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

            items . forEachIndexed { index, icon ->
                Box(
                    modifier = Modifier.size(60.dp)
                        .clip(CircleShape)
                        .background(
                            if (selected == index)
                            Color.Red
                            else
                                Color.Transparent
                        )
                        .clickable {
                            selected = index
                        }
                    ,
                    contentAlignment = Alignment.Center

                    

                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier =  Modifier.size(40.dp)
                    )

                }



            }



    }

}