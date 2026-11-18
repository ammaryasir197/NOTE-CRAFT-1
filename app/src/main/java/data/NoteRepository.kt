package data

class NoteRepository
    (private val dao: Dao) {

    suspend fun insertNote(note: Notes) {
        dao.getNote(note)
    }
    suspend fun getAllNotes(): List<Notes> {
        return dao.getAllnotes()
    }

    suspend fun deleteNote(note: Notes) {
        dao.deleteNote(note)
    }

    suspend fun updateNote(note: Notes) {
        dao.updateNote(note)
    }
}