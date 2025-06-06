package com.example.managemate.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable // Must be from androidx.databinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.managemate.repository.ContactRepository
import com.example.managemate.room.Contact
import kotlinx.coroutines.launch

// ViewModel: Holds and manages UI-related data for the Activity/Fragment
// Keeps the data alive even during configuration changes (like screen rotation)
// Helps separate UI logic from business/data logic
class ContactViewModel(private val repository: ContactRepository) : ViewModel(), Observable {

    // Observable interface implementation allows the ViewModel to notify observers
    // (like UI components) about data changes. Used when not using LiveData.
    // i) Make sure dataBinding = true is enabled in build.gradle

    // LiveData from repository — observed by UI to auto-update when data changes
    val contacts = repository.contacts

    // Flag to determine if the current operation is an update/delete (true) or a new insert (false)
    private var isUpdateOrDelete = false

    // Holds the contact that is selected for update or delete operations
    private lateinit var contactToUpdateOrDelete: Contact

    // Data Binding helps update UI automatically when data changes
    // LiveData holds observable data, MutableLiveData allows us to change it

    // Used with two-way data binding to observe & update the EditText input
    // These are user input fields connected to the form through XML (via data binding)
    @Bindable
    val inputName  = MutableLiveData<String?>()
    @Bindable
    val inputEmail = MutableLiveData<String?>()

    // Button label text, changes depending on user action (Save/Update or Clear/Delete)
    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()
    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        // Set default labels when the screen first loads
        // These will change when user selects an existing contact
        saveOrUpdateButtonText.value = "S a v e"
        clearAllOrDeleteButtonText.value = "C l e a r   A l l"
    }

    // suspend functions must run inside a coroutine.
    // viewModelScope.launch is a coroutine scope tied to the ViewModel's lifecycle.
    // Use it whenever you need to call a suspend function (like Room DB operations)
    // without freezing the UI — the system handles lifecycle and cleanup for you.

    // Inserts a new contact into the database
    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)  // Calls suspend function to insert contact into DB
    }

    // Deletes a specific contact and resets the UI
    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)  // Calls suspend function to delete contact

        // Reset form fields and button text
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "S a v e"
        clearAllOrDeleteButtonText.value = "C l e a r   A l l"
    }

    // Updates an existing contact and resets the UI
    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)  // Calls suspend function to update contact

        // Reset form fields and button text
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "S a v e"
        clearAllOrDeleteButtonText.value = "C l e a r   A l l"
    }

    // Deletes all contacts from the database
    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()  // Calls suspend function to delete all contacts
    }

    // Handles both Save and Update actions using a single button
    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            // If updating, modify the existing contact's fields and call update
            contactToUpdateOrDelete.contactName = inputName.value!!
            contactToUpdateOrDelete.contactEmail = inputEmail.value!!
            update(contactToUpdateOrDelete)
        } else {
            // If saving, create and insert a new contact
            val name = inputName.value!!
            val email = inputEmail.value!!

            // Kotlin null-safety bypassed using `!!` – assume not null because input validation is expected
            insert(Contact(0, name, email))  // ID is 0 because Room auto-generates it

            // Reset form fields after insertion
            inputName.value = null
            inputEmail.value = null
        }
    }

    // Decides whether to clear all contacts or delete a selected one, based on current mode
    fun clearAllorDelete() {
        if (isUpdateOrDelete) {
            // If updating/deleting a selected contact
            delete(contactToUpdateOrDelete)
        } else {
            // If nothing is selected, delete all contacts
            clearAll()
        }
    }

    // This function prepares the UI and logic to update or delete a specific contact
    fun initUpdateAndDelete(contact: Contact) {
        // Set the input fields with the selected contact's existing data
        inputName.value = contact.contactName
        inputEmail.value = contact.contactEmail

        // Mark that we are in "update/delete" mode (not insert)
        isUpdateOrDelete = true

        // Store the selected contact to use it later for update or delete
        contactToUpdateOrDelete = contact

        // Change button texts to indicate update/delete actions
        saveOrUpdateButtonText.value = "U p d a t e"
        clearAllOrDeleteButtonText.value = "D e l e t e"
    }

    // Required because ViewModel implements Observable interface.
    // These methods are part of the Observable contract, but unused here since LiveData already handles change detection.

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        // Not used because LiveData handles updates automatically
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        // Not used because LiveData handles updates automatically
    }
}
