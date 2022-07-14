package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    private lateinit var rviNotesList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rviNotesList = view.findViewById(R.id.rviNotesList)

        val btnAddNote: FloatingActionButton = view.findViewById(R.id.btnAddNote)
        btnAddNote.setOnClickListener{
            goToNoteDetail()
        }
    }

    private fun goToNoteDetail(id: Long = 0L){
        val action: NavDirections = ListFragmentDirections.actionGoToNoteFragment(id)
        Navigation.findNavController(rviNotesList).navigate(action)
    }
}