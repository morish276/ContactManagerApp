package com.example.contactmanagerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

// Android View Model class is a subclass of a view model and similar to them they are designed to store
// and manage UI related data that are responsible to prepare and provide data for the UI and automatically
// allow data to survive configuration changes so I can use a new constructor.

public class MyViewModel extends AndroidViewModel {
    // context inside viewmodel accepted if we use android view model
    // because it contains application context
    private  Repository myRepository;
    private LiveData<List<Contact>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.myRepository = new Repository(application);
    }

    public LiveData<List<Contact>> getAllContacts(){
        allContacts = myRepository.getAllContact();
        return allContacts;
    }

    public void addNewContact(Contact contact){
        myRepository.addContact(contact);
    }
    public  void delNewContact(Contact contact){
        myRepository.delContact(contact);
    }
}
