package com.example.contactmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Repository to handle data operations
public class Repository {
    private final ContactDao contactDao;
    ExecutorService executor;
    Handler handler;

    public Repository(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDao = contactDatabase.getContactDao();
    }

    // Methods in DAO being executed from Repo
    public void addContact(Contact contact){
        // Used for Background database operations
        executor = Executors.newSingleThreadExecutor();
        // Used for updating the UI
        handler = new Handler(Looper.getMainLooper());
        // Runnable: Executing tasks on separate thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contact);
            }
        });
    }
    public void delContact(Contact contact){
        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.delete(contact);
            }
        });
    }

    public LiveData<List<Contact>> getAllContact(){
        return contactDao.getAllContact();
    }
}
