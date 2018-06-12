package com.example.renanbenattidias.dbex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreatePositionActivity extends AppCompatActivity {

    TextView title;
    TextView positionNametextView;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_position);
        findViews();
        applyLaoyout();
        setUpOnClickListeners();
    }

    private void findViews() {
        title = findViewById(R.id.createPositionTitleTextView);
        positionNametextView = findViewById(R.id.positionNameTextView);
        saveButton = findViewById(R.id.savePositionButton);
    }

    private void applyLaoyout() {
        saveButton.setText("Save");
        title.setText("New Position");
        positionNametextView.setHint("Position name");
        positionNametextView.setText("");
    }

    private void setUpOnClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()) {
                    save();
                } else {
                    showErrorToastWith("Something went wrong getting the text!!");
                }
                finish();
            }
        });
    }

    private void save() {
        String name = positionNametextView.getText().toString();
        Position position = new Position(name);
        DBHelper database = new DBHelper(this);
        if(database.create(position)) {
            finish();
        } else {
            showErrorToastWith("Something went wrong saving!!");
        }
    }

    private void showErrorToastWith(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private boolean valid() {
        String name = positionNametextView.getText().toString();
        return name != null && !name.isEmpty();
    }

}
