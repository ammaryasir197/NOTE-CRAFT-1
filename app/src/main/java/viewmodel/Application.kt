package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Application: ViewModel() {
    var email by mutableStateOf("")
    private set
    var password by mutableStateOf("")
        private set
    fun updateEmail(newEmail:String){
        email = newEmail


    }
    fun updatePassword(newPassword:String){
         password = newPassword

    }
}