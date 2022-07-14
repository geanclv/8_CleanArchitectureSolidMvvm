package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.R
import com.geancarloleiva.core.data.Note
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NoteListAdapter(var lstNote: ArrayList<Note>, val action: ListAction)
    : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val noteLayout: CardView = view.findViewById(R.id.noteLayout)
        private val lblTitle: TextView = view.findViewById(R.id.lblTitle)
        private val lblContent: TextView = view.findViewById(R.id.lblContent)
        private val lblDate: TextView = view.findViewById(R.id.lblDate)
        private val lblWordCount: TextView = view.findViewById(R.id.lblWordCount)

        fun bind(note: Note){
            lblTitle.text = note.title
            lblContent.text = note.content
            val sdf = SimpleDateFormat("MMM dd, HH:mm")
            val resultDate = Date(note.updateTime)
            lblDate.text = "Last update: ${sdf.format(resultDate)}"
            lblWordCount.text = "Words: ${note.wordCount}"

            noteLayout.setOnClickListener{
                action.onClick(note.id)
            }
        }
    }

    fun updateNotes(newNotes: List<Note>){
        lstNote.clear()
        lstNote.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(lstNote[position])
    }

    override fun getItemCount(): Int {
        return lstNote.size
    }
}