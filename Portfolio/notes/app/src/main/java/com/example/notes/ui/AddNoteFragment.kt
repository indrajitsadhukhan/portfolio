package com.example.notes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.db.Note
import com.example.notes.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnsave: FloatingActionButton = view.findViewById(R.id.btnsave)
        val editTextTitle: EditText = view.findViewById(R.id.edit_text_title)
        val editTextNote: EditText = view.findViewById(R.id.edit_text_note)
        btnsave.setOnClickListener{vie->
            val noteTitle= editTextTitle.text.toString()
            val noteBody = editTextNote.text.toString()
            if(noteTitle.isEmpty())
            {
                editTextTitle.error="Title required"
                editTextTitle.requestFocus()
                return@setOnClickListener
            }
            if(noteBody.isEmpty())
            {
                editTextNote.error="Note required"
                editTextNote.requestFocus()
                return@setOnClickListener
            }
            val note = Note(noteTitle,noteBody)
            launch{
                NoteDatabase(view.context).getNoteDao().addNote(note)
                view.context.toast("Note Saved")
                val action = AddNoteFragmentDirections.actionSaveNote()
                Navigation.findNavController(vie).navigate(action)
            }
        }

    }

}