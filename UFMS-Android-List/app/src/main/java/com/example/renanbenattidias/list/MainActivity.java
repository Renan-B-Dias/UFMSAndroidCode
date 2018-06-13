package com.example.renanbenattidias.list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton goToNextActivity;
    TextView nameTextView;
    EditText nameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setUpClick();
        applyLayout();
    }

    private void applyLayout() {

    }

    private void findViews() {
        goToNextActivity = findViewById(R.id.goToNextButton);
        nameTextView = findViewById(R.id.nameTextView);
        nameEditText = findViewById(R.id.nameEditText);
    }

    private void setUpClick() {
        goToNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = getName();
                if(name != null && !name.isEmpty())
                    startSecondActivity(name);
                else {
                    showErrorDialog();
                }
            }
        });
    }

    private void showErrorDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.alertErrorTitle);
        dialog.setMessage(R.string.alertErrorText);
        dialog.show();
    }

    private void startSecondActivity(String name) {
        Intent goToNextIntent = new Intent(this, SecondActivity.class);
        goToNextIntent.putExtra("name", name);
        startActivity(goToNextIntent);
    }

    private String getName() {
        return nameEditText.getText().toString();
    }
}
