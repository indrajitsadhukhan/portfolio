package com.example.notes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.R
import com.example.notes.db.Note
import com.example.notes.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    lateinit var btnadd: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnadd = view.findViewById(R.id.btnadd)
        val recycler : RecyclerView = view.findViewById(R.id.recycler)
        recycler.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        launch {
            val note = context?.let {
              val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                recycler.adapter =NotesAdapter(notes)
            }
        }
        btnadd.setOnClickListener {
            val action = HomeFragmentDirections.actionaddNote(Note("",""))
            Navigation.findNavController(it).navigate(action)
        }

    }

}
