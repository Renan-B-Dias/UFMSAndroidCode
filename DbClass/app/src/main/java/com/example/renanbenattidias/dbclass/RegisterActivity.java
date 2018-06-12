package com.example.renanbenattidias.dbclass;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;
    EditText nameEditText;
    Button finishRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();
        applyLayout();
        setRegisterButton();
    }

    private void findViews() {
        emailEditText = findViewById(R.id.registerEmailEditText);
        passwordEditText = findViewById(R.id.registerPasswordEditText);
        repeatPasswordEditText = findViewById(R.id.registerRepeatPasswordEditText);
        nameEditText = findViewById(R.id.registerNameEditText);
        finishRegisterButton = findViewById(R.id.finishRegisterButton);
    }

    private void applyLayout() {
        emailEditText.setText(R.string.empty);
        passwordEditText.setText(R.string.empty);
        repeatPasswordEditText.setText(R.string.empty);
        nameEditText.setText(R.string.empty);
        finishRegisterButton.setText(R.string.register);

        emailEditText.setHint(R.string.emailPlaceholder);
        passwordEditText.setHint(R.string.passwordPlaceholder);
        repeatPasswordEditText.setHint(R.string.passwordPlaceholder);
        nameEditText.setHint(R.string.namePlaceholder);
    }

    private void setRegisterButton() {
        finishRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(registerIsValid())
                    register();
                else
                    showErrorOnRegister();
            }
        });
    }

    private boolean registerIsValid() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String repeatPassword = repeatPasswordEditText.getText().toString();
        String name = nameEditText.getText().toString();

        if(email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || name.isEmpty())
            return false;

        if(!password.equals(repeatPassword))
            return false;

        return true;
    }

    private void register() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String name = nameEditText.getText().toString();

        DBHelper dataBase = new DBHelper(this);
        Contact contact = new Contact(email, password, name);
        if(dataBase.createContact(contact)) {
            finish();
        } else {
            showErrorOnRegister();
        }
    }

    private void showErrorOnRegister() {
        Toast.makeText(this, R.string.wrongFields, Toast.LENGTH_LONG).show();
    }

}
