package com.example.managemate.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.managemate.repository.ContactRepository

// ViewModelFactory is used to create ViewModels that need constructor parameters (like repository)
class ViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {

    // <T : ViewModel> means T is a generic type that must be a subclass of ViewModel
    // This function returns an instance of T (like ContactViewModel), created manually
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the requested ViewModel class is ContactViewModel
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            // If yes, return an instance of ContactViewModel with the repository injected
            return ContactViewModel(repository) as T // Cast to generic type T
        }
        // If the ViewModel class is not recognized, throw an error
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}