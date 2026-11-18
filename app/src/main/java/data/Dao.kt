package data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert
    suspend fun getNote(notes: Notes)
    @Query("SELECT * FROM notes")
    suspend fun getAllnotes(): List<Notes>
    @Delete
    suspend fun deleteNote(notes: Notes)
    @Update
    suspend fun updateNote(notes: Notes)
}