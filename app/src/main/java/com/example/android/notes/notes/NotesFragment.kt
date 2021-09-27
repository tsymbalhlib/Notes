package com.example.android.notes.notes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.notes.R
import com.example.android.notes.databinding.NotesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.notes_fragment) {

    private val notesViewModel: NotesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = NotesFragmentBinding.bind(view)

        binding.notesViewModel = notesViewModel

        binding.lifecycleOwner = this

        val adapter = NotesAdapter(notesViewModel)

        binding.notesList.adapter = adapter

        notesViewModel.navigateToAddNoteFragment.observe(viewLifecycleOwner, { eventNavigate ->
            eventNavigate.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    NotesFragmentDirections.actionNotesFragmentToAddNoteFragment()
                )
            }
        })

        notesViewModel.navigateToNoteDetailFragment.observe(viewLifecycleOwner, { eventNavigate ->
            eventNavigate.getContentIfNotHandled()?.let { note ->
                findNavController().navigate(
                    NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment(note)
                )
            }
        })

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_note -> {
                notesViewModel.onAddNote()
                true
            }
            R.id.menu_add_note -> {
                notesViewModel.onAddNote()
                true
            }
            R.id.menu_delete_marked -> {
                notesViewModel.onDeleteMarkedNotes()
                true
            }
            R.id.menu_delete_all -> {
                notesViewModel.onDeleteAllNotes()
                true
            }
            else -> false
        }
    }
}