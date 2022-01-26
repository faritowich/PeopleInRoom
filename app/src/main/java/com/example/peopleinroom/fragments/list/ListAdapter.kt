package com.example.peopleinroom.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.peopleinroom.R
import com.example.peopleinroom.model.User

class ListAdapter() : Adapter<ListAdapter.MyViewHolder>() {

    var userList = emptyList<User>()

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val idView: TextView = view.findViewById(R.id.id_txt)
        val firstNameView: TextView = view.findViewById(R.id.firstName_txt)
        val lastNameView: TextView = view.findViewById(R.id.lastName_txt)
        val ageView: TextView = view.findViewById(R.id.age_txt)
        val rowLayout: LinearLayout = view.findViewById(R.id.row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.idView.text = currentItem.id.toString()
        holder.firstNameView.text = currentItem.firstName
        holder.lastNameView.text = currentItem.lastName
        holder.ageView.text = currentItem.age.toString()

//        holder.rowLayout.setOnClickListener {
//            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
//        }


    }

    override fun getItemCount(): Int = userList.size

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}