 package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

 class MainActivity : AppCompatActivity(),NotesRVAdapter.INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var submit: Button
    lateinit var editnotes: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.recyclerView)
        submit= findViewById(R.id.submit)
        editnotes=findViewById(R.id.editnotes)
        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter=NotesRVAdapter(this,this)
        recyclerView.adapter=adapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })
        submit.setOnClickListener{
            val text = editnotes.text.toString()
            if(text.isNotEmpty())
            {
                viewModel.insertNote(Note(text))
                Toast.makeText(this,"Inserted",Toast.LENGTH_SHORT).show()
            }
        }
    }
     override fun onItemClicked(note: Note) {
viewModel.deleteNote(note)
Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()
     }
 }