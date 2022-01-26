package com.example.peopleinroom.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.peopleinroom.R
import com.example.peopleinroom.viewmodel.UserViewModel

class DeleteDialogFragment : DialogFragment() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_question))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(getString(R.string.delete)) { _,_ -> deleteAllData()}
            .setNegativeButton(getString(R.string.cancel)) { _,_ -> }
            .create()

    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }

    private fun deleteAllData(){
        viewModel.deleteAllData()
    }
}