package Newpakage

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class imageview: ViewModel() {
    var uri : Uri? by mutableStateOf(null)
    private set
    fun updateuri(neweuri : Uri?){
          uri = neweuri

    }

}