package com.example.contactmanagerapp;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class AddNewContactClickHandler {
    Contact contact;
    Context context;
    MyViewModel viewModel;

    public AddNewContactClickHandler(Contact contact, Context context, MyViewModel viewModel) {
        this.contact = contact;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitBtnClick(View view){
        if (contact.getName() == null || contact.getEmail() == null){
            Toast.makeText(context, "Fields cannot be Empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);
            Contact c = new Contact(
                    contact.getName(),contact.getEmail()
            );
            viewModel.addNewContact(c);
            context.startActivity(i);
        }
    }
}
