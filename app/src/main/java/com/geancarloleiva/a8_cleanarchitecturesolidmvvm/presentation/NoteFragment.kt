package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.presentation

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.Navigation
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.R
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.viewmodel.NoteViewModel
import com.geancarloleiva.core.data.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteFragment : Fragment() {

    private var noteId = 0L
    private lateinit var btnCreateNote: FloatingActionButton
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)

    private lateinit var txtTitle: EditText
    private lateinit var txtDetail: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if(noteId != 0L){
            viewModel.getNoteById(noteId)
        }

        txtTitle = view.findViewById(R.id.txtTitle)
        txtDetail = view.findViewById(R.id.txtDetail)
        btnCreateNote = view.findViewById(R.id.btnCreateNote)
        btnCreateNote.setOnClickListener{

            if(txtTitle.text.isNotEmpty() || txtDetail.text.isNotEmpty()){
                val time = System.currentTimeMillis()
                currentNote.title = txtTitle.text.toString()
                currentNote.content = txtDetail.text.toString()
                currentNote.updateTime = time

                if(currentNote.id == 0L){
                    currentNote.creationTime = time
                }

                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        //Observing the creation method
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(btnCreateNote).popBackStack()
            } else {
                Toast.makeText(context, "Error saving the note", Toast.LENGTH_SHORT).show()
            }
        })

        //Observing the get method
        viewModel.currentNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                currentNote = it
                txtTitle.setText(it.title)
                txtDetail.setText(it.content)
            }
        })
    }

    private fun hideKeyboard(){
        val inp = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inp.hideSoftInputFromWindow(btnCreateNote.windowToken, 0)
    }
}