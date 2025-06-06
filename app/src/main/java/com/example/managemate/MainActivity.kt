package com.example.managemate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.managemate.databinding.ActivityMainBinding
import com.example.managemate.repository.ContactRepository
import com.example.managemate.room.Contact
import com.example.managemate.room.ContactDatabase
import com.example.managemate.view.MyRecyclerViewAdapter
import com.example.managemate.viewmodel.ContactViewModel
import com.example.managemate.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    // Declare binding object for ActivityMainBinding (auto-generated from activity_main.xml)
    private lateinit var binding: ActivityMainBinding
    // ViewModel to handle all contact-related data logic
    private lateinit var contactViewModel: ContactViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        // Force Light Mode regardless of system setting
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        // Enables full-screen layout with edge-to-edge content display
        enableEdgeToEdge()
        // This line is redundant and gets replaced by the next one using DataBindingUtil
        setContentView(R.layout.activity_main)


        // Initialize binding using DataBindingUtil to enable direct XML → ViewModel binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize DAO (Data Access Object) for Room Database
        // This lets you perform insert, update, delete, query operations
        val dao = ContactDatabase.getInstance(applicationContext).contactDAO
        val repository = ContactRepository(dao)
        val factory = ViewModelFactory(repository)

        // ViewModel initialization using ViewModelProvider
        // Responsible for surviving configuration changes (e.g., screen rotation)
        // NOTE: Ideally should use a custom ViewModelFactory when ViewModel takes parameters
        contactViewModel = ViewModelProvider(this, factory).get(ContactViewModel::class.java)

        // Link ViewModel to XML using the name defined in <data> block of layout
        binding.contactViewModel = contactViewModel

        // Required for LiveData + DataBinding to work together
        // LifecycleOwner ensures LiveData only updates UI when the activity is in an active state
        binding.lifecycleOwner = this

        // Initialize the RecyclerView (sets layout manager and adapter)
        initRecyclerView()

        // Apply padding for system UI (status bar, nav bar) to avoid content overlap
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initRecyclerView() {
        // Set a vertical LinearLayoutManager to display items in a vertical list
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe LiveData from ViewModel and update RecyclerView accordingly
        DisplayUsersList()
    }

    private fun DisplayUsersList() {
        // Observes LiveData 'contacts' from the ViewModel
        // Automatically updates the RecyclerView when contact list changes
        contactViewModel.contacts.observe(this, Observer {
            // Sets custom adapter with two parameters:
            // 1. 'it' is the updated contact list from LiveData
            // 2. Lambda to handle item click; passes selected item to a function
            binding.recyclerView.adapter = MyRecyclerViewAdapter(it, { selectedItem: Contact ->
                listItemClicked(selectedItem)
            })
        })
    }

    private fun listItemClicked(selectedItem: Contact) {
        // Displays a toast with the selected contact's name
        Toast.makeText(this, "Selected name is ${selectedItem.contactName}", Toast.LENGTH_LONG).show()

        // Passes the selected item to the ViewModel to handle update/delete logic
        contactViewModel.initUpdateAndDelete(selectedItem)
    }

}
