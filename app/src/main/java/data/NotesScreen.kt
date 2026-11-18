
package ui

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.NoteViewModel
import data.Notes

@Composable
fun NotesScreen(viewModel: NoteViewModel) {

    // ADD NOTE STATES
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // EDIT NOTE STATES
    var selectedNote by remember { mutableStateOf<Notes?>(null) }
    var editTitle by remember { mutableStateOf("") }
    var editDescription by remember { mutableStateOf("") }

    // CONTEXT FOR TOAST
    val context = LocalContext.current

    // GET NOTES FROM VIEWMODEL
    val notes by viewModel.notes.collectAsState()


    // MAIN UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "My Notes",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TITLE TEXTFIELD
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = {
                Text(
                    "Enter Title",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RoundedCornerShape(30.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // DESCRIPTION TEXTFIELD
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            placeholder = {
                Text(
                    "Enter Description",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, Color.Black, RoundedCornerShape(30.dp)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ADD NOTE BUTTON
        Button(
            onClick = {

                if (title.isNotBlank() && description.isNotBlank()) {

                    val note = Notes(
                        title = title,
                        description = description
                    )

                    viewModel.insertNote(note)

                    title = ""
                    description = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Note")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Saved Notes",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))


        // DISPLAY NOTES
        LazyColumn {

            items(notes) { note ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        // NOTE TITLE AND DESCRIPTION
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = note.title,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(text = note.description)
                        }

                        // DELETE AND EDIT BUTTONS
                        Column {

                            TextButton(
                                onClick = {
                                    viewModel.deleteNote(note)
                                }
                            ) {
                                Text("Delete")
                            }

                            TextButton(
                                onClick = {

                                    selectedNote = note

                                    editTitle = ""
                                    editDescription = ""
                                }
                            ) {
                                Text("Edit")
                            }
                        }
                    }
                }
            }
        }
    }


    // EDIT ALERT DIALOG
    if (selectedNote != null) {

        AlertDialog(

            onDismissRequest = {
                selectedNote = null
            },

            title = {
                Text("Edit Note")
            },

            text = {

                Column {

                    OutlinedTextField(
                        value = editTitle,
                        onValueChange = {
                            editTitle = it
                        },
                        label = {
                            Text("New Title")
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editDescription,
                        onValueChange = {
                            editDescription = it
                        },
                        label = {
                            Text("New Description")
                        }
                    )
                }
            },

            // CONFIRM BUTTON
            confirmButton = {

                TextButton(
                    onClick = {

                        val oldNote = selectedNote

                        if (oldNote != null) {

                            val updatedNote = oldNote.copy(

                                title = if (editTitle.isNotBlank()) {
                                    editTitle.trim()
                                } else {
                                    oldNote.title
                                },

                                description = if (editDescription.isNotBlank()) {
                                    editDescription.trim()
                                } else {
                                    oldNote.description
                                }
                            )

                            if (
                                editTitle.isNotBlank() ||
                                editDescription.isNotBlank()
                            ) {

                                viewModel.updateNote(updatedNote) {

                                    Toast.makeText(
                                        context,
                                        "Successfully Edited!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    selectedNote = null
                                }
                            }
                        }
                    }
                ) {
                    Text("Done")
                }
            },

            // CANCEL BUTTON
            dismissButton = {

                TextButton(
                    onClick = {
                        selectedNote = null
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
