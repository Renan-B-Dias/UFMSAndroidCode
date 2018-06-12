package com.example.renanbenattidias.dbclass;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedActivity extends AppCompatActivity {

    TextView welcomeTextView;
    TextView userNameTextView;

    Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        findViews();
        setCloseButton();
        applyLayout();
    }

    private void findViews() {
        welcomeTextView = findViewById(R.id.welcomeTextView);
        userNameTextView = findViewById(R.id.welcomeUserTextView);
        closeButton = findViewById(R.id.closeWelcomeButton);
    }

    private void applyLayout() {
        welcomeTextView.setText(R.string.welcome);
        closeButton.setText(R.string.logout);
        Contact contact = (Contact) getIntent().getExtras().getSerializable("contact");
        if(contact != null) {
            userNameTextView.setText(contact.name());
        }

        userNameTextView.setTypeface(null, Typeface.BOLD);
        welcomeTextView.setTypeface(null, Typeface.BOLD);

        userNameTextView.setTextSize(24);
        welcomeTextView.setTextSize(24);
    }

    private void setCloseButton() {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
