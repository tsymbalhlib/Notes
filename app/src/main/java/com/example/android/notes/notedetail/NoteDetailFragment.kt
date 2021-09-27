package com.example.android.notes.notedetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.notes.R
import com.example.android.notes.databinding.NoteDetailFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteDetailFragment : Fragment(R.layout.note_detail_fragment) {

    private val noteDetailViewModel: NoteDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = NoteDetailFragmentBinding.bind(view)

        binding.noteDetailViewModel = noteDetailViewModel

        binding.lifecycleOwner = this

        noteDetailViewModel.navigateToNotesFragment.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment()
                )
            }
        })

        noteDetailViewModel.showSnackBarEventValue.observe(viewLifecycleOwner, { eventMessage ->
            eventMessage.getContentIfNotHandled()?.let { message ->
                Snackbar.make(
                    binding.root,
                    message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_detail_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.update_note -> {
                noteDetailViewModel.onUpdateNoteClicked()
                true
            }
            R.id.menu_update_note -> {
                noteDetailViewModel.onUpdateNoteClicked()
                true
            }
            R.id.delete_note -> {
                noteDetailViewModel.onDeleteNote()
                true
            }
            R.id.menu_delete_note -> {
                noteDetailViewModel.onDeleteNote()
                true
            }
            else -> false
        }
    }
}