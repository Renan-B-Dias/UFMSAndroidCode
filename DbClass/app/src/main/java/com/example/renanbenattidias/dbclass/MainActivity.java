package com.example.renanbenattidias.dbclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView loginTextView;

    EditText emailEditText;
    EditText passwordEditText;

    Button registerButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        applyLayout();
        setRegisterButton();
        setLoginButton();
    }

    private void findViews() {
        loginTextView = findViewById(R.id.loginTextView);

        emailEditText= findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);
    }

    private void applyLayout() {
        loginTextView.setText(R.string.login);

        emailEditText.setText("");
        emailEditText.setHint(R.string.emailPlaceholder);
        passwordEditText.setText("");
        passwordEditText.setHint(R.string.passwordPlaceholder);

        registerButton.setText(R.string.register);
        loginButton.setText(R.string.login);
    }

    private void setRegisterButton() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });
    }

    private void setLoginButton() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(!email.isEmpty() && !password.isEmpty()) {
                    Contact contact = authenticate(email, password);
                    if(contact != null) {
                        login(contact);
                        return;
                    }
                }
                showAuthenticationErrorToast();
            }
        });
    }

    private void showAuthenticationErrorToast() {
        Toast.makeText(this, R.string.authenticationError, Toast.LENGTH_LONG).show();
    }

    private Contact authenticate(String email, String password) {
        DBHelper database = new DBHelper(this);
        Contact contact = database.findContactBy(email);
        if(contact != null && contact.password().equals(password))
            return contact;
        return null;
    }

    private void login(Contact contact) {
        Intent loggedView = new Intent(this, LoggedActivity.class);
        loggedView.putExtra("contact", contact);
        startActivity(loggedView);
    }

    private void goToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
