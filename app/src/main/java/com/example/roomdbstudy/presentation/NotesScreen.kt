package com.example.roomdbstudy.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomdbstudy.data.local.entity.Note

@Composable
fun NotesScreen(
    state: NoteState,
    addNote: (Note) -> Unit,
    deleteNote: (Note) -> Unit
) {
    // initialize mutableStateOf variables to hold text data entered by user for adding notes
    var noteTitle by remember { mutableStateOf("") }
    var noteDescription by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Notes App",
            fontSize = 50.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(16.dp)
        )

        // Input field to add note
        OutlinedTextField(
            value = noteTitle,
            onValueChange = { noteTitle = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = noteDescription,
            onValueChange = { noteDescription = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            enabled = noteTitle.isNotBlank() && noteDescription.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                if(noteTitle.isNotBlank() && noteDescription.isNotBlank())
                {
                    addNote(Note(title = noteTitle, description = noteDescription))
                    noteTitle = ""
                    noteDescription = ""
                }
            }
        ) { Text(
            text = "Add Note") }

        Spacer(Modifier.height(16.dp))
        when (state) {
            is NoteState.Loading -> {
                CircularProgressIndicator()
            }

            is NoteState.Success -> {
                var notesList = state.data
                LazyColumn() {
                    items(notesList) { note ->
                        // add NoteItem
                        NoteItem(note, onDelete = { deleteNote(note) })
                    }
                }
            }

            is NoteState.Error -> {

            }

            is NoteState.Empty -> {}
            else -> {}
        }


    }
}

@Composable
fun NoteItem(
    note: Note,
    onDelete:(Note) -> Unit
){
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(text = note.title )
                Text(note.description)
            }
            Spacer(Modifier.weight(1f)) // since its in a row will move to far right side
            IconButton(onClick = {onDelete(note)}) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete note")            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NotesScreen(
        NoteState.Success(
            listOf(
                Note(id = 2, title = "My first note", "This is a great note")
            ),
        ),
        addNote = {},
        deleteNote = {}
    )
}