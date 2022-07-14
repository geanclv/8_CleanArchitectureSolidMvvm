package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.R
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.viewmodel.ListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(), ListAction {

    private lateinit var rviNotesList: RecyclerView
    private val noteAdapter: NoteListAdapter = NoteListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        rviNotesList = view.findViewById(R.id.rviNotesList)
        rviNotesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteAdapter
        }

        val btnAddNote: FloatingActionButton = view.findViewById(R.id.btnAddNote)
        btnAddNote.setOnClickListener{
            goToNoteDetail()
        }

        progressBar = view.findViewById(R.id.progressBar)

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun observeViewModel(){
        viewModel.lstNotes.observe(viewLifecycleOwner, Observer{
            progressBar.visibility = View.GONE
            rviNotesList.visibility = View.VISIBLE
            noteAdapter.updateNotes(it.sortedByDescending { it.updateTime })
        })
    }

    private fun goToNoteDetail(id: Long = 0L){
        val action: NavDirections = ListFragmentDirections.actionGoToNoteFragment(id)
        Navigation.findNavController(rviNotesList).navigate(action)
    }

    override fun onClick(id: Long) {
        goToNoteDetail(id)
    }


}