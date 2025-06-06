package com.example.managemate.repository

import com.example.managemate.room.Contact
import com.example.managemate.room.ContactDAO

// Repository: acts as bridge b/w the Viewmodel an Data Source
class ContactRepository(private val contactDAO: ContactDAO) {
     val contacts = contactDAO.getAllContactsInDB()

     // Why use Repository even though DAO already has these methods?
     // 1. Hides low-level DB code from ViewModel (clean separation)
     // 2. Easier to switch data source later (like Firebase or API)
     // 3. Keeps all data logic in one place

     suspend fun insert(contact: Contact):Long{
          return contactDAO.insertContact(contact)
     }

     suspend fun delete(contact: Contact){
          return contactDAO.deleteContact(contact)
     }

     suspend fun update(contact: Contact){
          return contactDAO.updateContact(contact)
     }

     suspend fun deleteAll(){
          return contactDAO.deleteAll()
     }
}