package com.example.renanbenattidias.dbcontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    Button createConteactButton;

    ArrayAdapter<String> adapter;
//    private String[] contactListString = new String[] {};
    private List<Contact> contactListing = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setClickListeners();
        applyTexts();
        applyLayout();
//        contactListString = getContacts();
        contactListing = getContacts();
        configureListView();
//        reloadData();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        reloadData();
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        reloadData();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        reloadData();
//    }

    private void configureListView() {
        String[] contactArrayString = new String[] {};

        int i = 0;
        for(Contact contact: contactListing) {
            contactArrayString[i++] = contact.name();
        }

        adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1,
                android.R.id.text1, contactArrayString);
        contactList.setAdapter(adapter);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToUpdate(contactListing.get(i));
            }
        });
    }

    private void reloadData() {
//        contactListString = getContacts();
        contactListing = getContacts();
        adapter.notifyDataSetChanged();
    }

    private List<Contact> getContacts() {
        DBContactHelper database = new DBContactHelper(this);
        List<Contact> contacts = database.getContacts();
        database.close();
        return contacts;
    }

//    private String[] getContacts() {
//
//        DBContactHelper database = new DBContactHelper(this);
//        List<Contact> contacts = database.getContacts();
//
//        List<String> names = new ArrayList<>();
//        for(Contact contact: contacts) {
//            names.add(contact.name() + ": Telefone: " + contact.phone());
//        }
//
//        database.close();
//
//        return names.toArray(new String[] {});
//    }

    private void findViews() {
        contactList = findViewById(R.id.contactListView);
        createConteactButton = findViewById(R.id.createNewContactButton);
    }

    private void setClickListeners() {
        createConteactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateView();
            }
        });
    }

    private void applyTexts() {
        createConteactButton.setText(R.string.newContact);
    }

    private void applyLayout() {

    }

    private void goToCreateView() {
        Intent createContactView = new Intent(this, RegisterContactActivity.class);
        startActivity(createContactView);
    }

    private void goToUpdate(Contact contact) {
        Intent updateContactView = new Intent(this, RegisterContactActivity.class);
        updateContactView.putExtra("contact", contact);
        startActivity(updateContactView);
    }
}
