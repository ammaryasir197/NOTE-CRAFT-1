package data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun getDatabase(context: Context): NoteDatabas {

        return Room.databaseBuilder(
            context,
            NoteDatabas::class.java,
            "notes_database"
        ).build()

    }

}