package com.example.android.notes.addnote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.notes.R
import com.example.android.notes.databinding.AddNoteFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.add_note_fragment) {

    private val addNoteViewModel: AddNoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = AddNoteFragmentBinding.bind(view)

        binding.addNoteViewModel = addNoteViewModel

        binding.lifecycleOwner = this

        addNoteViewModel.navigateToNotesFragment.observe(viewLifecycleOwner, { event ->
            event.getContentIfNotHandled()?.let {
                findNavController().navigate(
                    AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment()
                )
            }

        })

        addNoteViewModel.showSnackBarEventValue.observe(viewLifecycleOwner, { eventMessage ->
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
        inflater.inflate(R.menu.add_note_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_note -> {
                addNoteViewModel.onSaveNoteClicked()
                true
            }
            R.id.menu_save_note -> {
                addNoteViewModel.onSaveNoteClicked()
                true
            }
            else -> false
        }
    }
}