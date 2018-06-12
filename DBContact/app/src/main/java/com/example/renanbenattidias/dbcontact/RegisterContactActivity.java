package com.example.renanbenattidias.dbcontact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_contact);
        findViews();
        setClickListeners();
        applyTexts();
        applyLayout();
    }

    private void findViews() {
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        saveButton = findViewById(R.id.saveButton);
    }

    private void setClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void update() {

    }

    private void register() {
        String name = nameEditText.getText().toString();
        String ageString = ageEditText.getText().toString();
        Integer age = Integer.parseInt(ageString);
        String address= addressEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        Contact contact = tryToCreateContact(name, age, address, phone);
        if(contact != null) {
            DBContactHelper database = new DBContactHelper(this);
            if(database.create(contact)) {
                finish();
            } else {
                showErrorToast(getResources().getString(R.string.errorToast));
            }
        } else {
            showErrorToast(getResources().getString(R.string.errorToast));
        }
    }

    private void showErrorToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private Contact tryToCreateContact(String name, Integer age, String phone, String address) {
        if(name.isEmpty() || age == null || phone.isEmpty() || address.isEmpty())
            return null;
        return new Contact(name, age, phone, address);
    }

    private void applyTexts() {
        nameEditText.setText("");
        nameEditText.setHint(R.string.name);

        ageEditText.setText("");
        ageEditText.setHint(R.string.age);

        addressEditText.setText("");
        addressEditText.setHint(R.string.address);

        phoneEditText.setText("");
        phoneEditText.setHint(R.string.phone);

        saveButton.setText(R.string.save);
    }

    private void applyLayout() {

    }
}
