package com.example.peopleinroom.fragments.delete

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.peopleinroom.R
import com.example.peopleinroom.model.User
import com.example.peopleinroom.viewmodel.UserViewModel

class DeleteDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_question))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(getString(R.string.delete)) { _,_ ->
                deleteAllData()}
            .setNegativeButton(getString(R.string.cancel)) { _,_ -> }
            .create()
    private val viewModel: UserViewModel by viewModels()


    companion object {
        const val TAG = "PurchaseConfirmationDialog"
    }

    private fun deleteAllData(){
        viewModel.deleteAllData()
    }

}