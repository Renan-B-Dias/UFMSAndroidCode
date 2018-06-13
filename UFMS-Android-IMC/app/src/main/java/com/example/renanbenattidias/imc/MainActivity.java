package com.example.renanbenattidias.imc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText userEditText;
    EditText passwordEditText;
    Button loginButton;

    TextView userNameTextView;
    TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpViews();
        applyLayout();
        setLoginClick();
    }

    private void setUpViews() {
        userEditText = findViewById(R.id.userEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        userNameTextView = findViewById(R.id.userNameTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
    }

    private void applyLayout() {
        loginButton.setText(R.string.loginButton);
        loginButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        userNameTextView.setText(R.string.user);
        passwordTextView.setText(R.string.password);
    }

    private void setLoginClick() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSecondView();
            }
        });
    }

    private String getUserName() {
        return userEditText.getText().toString();
    }

    private String getUserPassword() {
        return passwordEditText.getText().toString();
    }

    private void startSecondView() {
        String name = getUserName();
        String password = getUserPassword();
        if(!name.isEmpty() && !password.isEmpty()) {
            Intent secondView = new Intent(this, SecondActivity.class);
            secondView.putExtra("name", name);
            secondView.putExtra("userPassword", password);
            startActivity(secondView);
        }
    }
}
