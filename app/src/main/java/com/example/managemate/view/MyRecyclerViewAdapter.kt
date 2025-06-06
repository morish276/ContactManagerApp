package com.example.managemate.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.managemate.R
import com.example.managemate.databinding.CardItemBinding
import com.example.managemate.room.Contact

class MyRecyclerViewAdapter(
    private val contactsList: List<Contact>, // List of Contact data to display
    private val clickListener: (Contact) -> Unit // Lambda function to handle item click events
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    // ViewHolder class holds reference to views for each list item
    class MyViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        // binding.root refers to the root layout of the item view (e.g., LinearLayout, ConstraintLayout)
        // RecyclerView needs a View object; binding.root provides that

        // Binds data to views and sets click listener
        fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
            binding.tvName.text = contact.contactName // Set name text
            binding.tvEmail.text = contact.contactEmail // Set email text

            binding.listItemLayout.setOnClickListener {
                clickListener(contact) // Invoke click listener with the current contact
            }
        }
    }

    // Called when RecyclerView needs a new item view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context) // Create inflater from context

        // Inflate layout using DataBindingUtil; returns a binding object
        val binding: CardItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.card_item, // Custom XML layout for each item
            parent,
            false // Don't attach to parent yet
        )

        return MyViewHolder(binding) // Return new ViewHolder with the binding
    }

    // Returns total number of items to be displayed
    override fun getItemCount(): Int {
        return contactsList.size
    }

    // Called to bind data at a given position to the ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(contactsList[position], clickListener) // Bind current contact and click listener
    }
}