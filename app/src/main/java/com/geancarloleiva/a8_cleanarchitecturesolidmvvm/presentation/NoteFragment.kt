package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.presentation

import android.app.AlertDialog
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
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

        if (noteId != 0L) {
            viewModel.getNoteById(noteId)
        }

        txtTitle = view.findViewById(R.id.txtTitle)
        txtDetail = view.findViewById(R.id.txtDetail)
        btnCreateNote = view.findViewById(R.id.btnCreateNote)

        //Adding and updating a Note
        btnCreateNote.setOnClickListener {
            if (txtTitle.text.isNotEmpty() || txtDetail.text.isNotEmpty()) {
                val time = System.currentTimeMillis()
                currentNote.title = txtTitle.text.toString()
                currentNote.content = txtDetail.text.toString()
                currentNote.updateTime = time

                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
                }

                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        //Using the MenuProvider defined in MainActivity to create the options menu in this fragment
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //Adding menu items
                menuInflater.inflate(R.menu.menu_note, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                //Handling menu selection
                return when (menuItem.itemId) {
                    R.id.deleteNote -> {
                        if (context != null && noteId != 0L) {
                            AlertDialog.Builder(context!!)
                                .setTitle("Delete note")
                                .setMessage("Are you sure you want to delete the note?")
                                .setPositiveButton("Yes") { dialogInterface, i ->
                                    viewModel.deleteNote(currentNote)
                                }
                                .setNegativeButton("Cancel") { dialogInterface, i ->

                                }
                                .create()
                                .show()
                        }
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        observeViewModel()
    }

    private fun observeViewModel() {
        //Observing the creation method
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
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

        //Observing the delete method
        viewModel.deleted.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(btnCreateNote).popBackStack()
            } else {
                Toast.makeText(context, "Error deleting the note", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideKeyboard() {
        val inp = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inp.hideSoftInputFromWindow(btnCreateNote.windowToken, 0)
    }
}