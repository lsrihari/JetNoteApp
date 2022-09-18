package com.example.myapplication.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.component.NoteButton
import com.example.myapplication.component.NoteInputText
import com.example.myapplication.data.NoteDataSource
import com.example.myapplication.model.Note
import com.example.myapplication.util.formatDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {

        val context = LocalContext.current
        TopAppBar(
            title = { Text(text = context.getString(R.string.app_name)) },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon"
                )
            },
            backgroundColor = Color(0xFF6BBAFA)
        )
        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isWhitespace() || char.isLetter()
                        }) title = it
                })
            NoteInputText(
                Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char ->
                            char.isWhitespace() || char.isLetter()
                        }) description = it
                })
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    //save and add to list
                    onAddNote(Note(title=title,description=description))
                    title = ""
                    description = ""
                    Toast.makeText(context,"Note Addded",Toast.LENGTH_SHORT)
                }
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn() {
            items(notes) { note ->
                NoteRow(note = note, onNoteClicked ={
                    onRemoveNote(note)
                } )
            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun NotesScreenPreview() {
//    NoteScreen(notes = NoteDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFF36AEDD),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)
                    Log.d("TAG", "NoteRow: clicked")
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time),
            style = MaterialTheme.typography.caption)
        }
    }
}

//@Composable
//fun DummyComposable() {
//
//}
//
//
//fun PotatoComposable() {
//
//}
//
//@Composable
//fun AnotherDummy(composable: (text:String,text2:String) -> Unit,modifier:Modifier) {
//
//    composable("","")
//}
//fun add(a:Number, b:Number) {
//    val c = a+b
//}
//
//
//
//@Composable
//fun Parent() {
//    add(5,6)
//    AnotherDummy(composable = {
//                              text, text2 ->
//
//                              }, modifier = Modifier)
//}