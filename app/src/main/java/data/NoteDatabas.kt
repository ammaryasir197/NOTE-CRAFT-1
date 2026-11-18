package data

import android.os.Build
import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Notes::class],  version = 1)
abstract class NoteDatabas: RoomDatabase() {
    abstract fun notesDao():Dao

}


