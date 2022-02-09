package com.example.peopleinroom.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.peopleinroom.MainActivity
import com.example.peopleinroom.R
import com.example.peopleinroom.databinding.FragmentAddBinding
import com.example.peopleinroom.databinding.FragmentUpdateBinding
import com.example.peopleinroom.fragments.DeleteDialogFragment
import com.example.peopleinroom.model.User
import com.example.peopleinroom.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    lateinit var binding: FragmentUpdateBinding
    val viewModel: UserViewModel by viewModels()
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        binding.updateFirstNameEt.setText(args.currentUser.firstName)
        binding.updateLastNameEt.setText(args.currentUser.lastName)
        binding.updateAgeEt.setText(args.currentUser.age.toString())

        binding.updateBtn.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = binding.updateAgeEt.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            // Create User Object
            val updatedUser = User(args.currentUser.id, firstName, lastName, Integer.parseInt(age))
            // Update Data in Database
            viewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "User updated!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(
            age
        ))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_button) {
            deleteUser()
        }
        else {
            (activity as MainActivity).onSupportNavigateUp()
        }
        return true
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.delete)) { _, _ ->
            viewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "User deleted!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure to delete this user?")
        builder.create().show()
    }
}