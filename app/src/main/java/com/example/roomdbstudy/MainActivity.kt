package com.example.roomdbstudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.roomdbstudy.presentation.NotesScreen
import com.example.roomdbstudy.presentation.NotesViewModel
import com.example.roomdbstudy.ui.theme.RoomDBStudyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomDBStudyTheme {
                val viewModel: NotesViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsState()
                //val delete = {note -> viewModel.deleteNote()}
                //val addNote = {note -> viewModel.insertNote(note)}

                NotesScreen(
                    state = state,
                    addNote = { note -> viewModel.insertNote(note) },
                    deleteNote = { note -> viewModel.deleteNote(note) }
                )

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDBStudyTheme {
        Greeting("Android")
    }
}