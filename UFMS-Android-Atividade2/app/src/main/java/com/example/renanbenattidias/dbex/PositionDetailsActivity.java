package com.example.renanbenattidias.dbex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PositionDetailsActivity extends BaseActivity {

    private TextView title;
    private EditText nameEditText;
    private Button saveButton;
    private Button deleteButton;

    private Position position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutReference = R.layout.activity_position_details;
        super.onCreate(savedInstanceState);
        position = (Position) getIntent().getSerializableExtra("position");
        nameEditText.setText(position.name);
        setUpOnClickListeners();
    }

    @Override
    public void findViews() {
        title = findViewById(R.id.positionDetailsTitle);
        nameEditText = findViewById(R.id.positionDetailsNameTextField);
        saveButton = findViewById(R.id.positionDetailsSaveButton);
        deleteButton = findViewById(R.id.positionDetailsDeleteButton);
    }

    @Override
    public void applyLayot() {
        title.setText("Details");
        saveButton.setText("Save");
        deleteButton.setText("Delete");
    }

    private void setUpOnClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(canEdit()) {
                    edit();
                } else {
                    showErrorToastWith("Fields are invalid");
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private boolean canEdit() {
        return !nameEditText.getText().toString().isEmpty();
    }

    private void delete() {
        PositionDataBase database = new DBHelper(this);
        if(database.delete(position.id)) {
            finish();
        } else {
            showErrorToastWith("Something went wrong deleting!!");
        }
    }

    private void edit() {
        String newName = nameEditText.getText().toString();
        position.name = newName;
        PositionDataBase database = new DBHelper(this);
        if(database.update(position)) {
            finish();
        } else {
            showErrorToastWith("Something went wrong editing!!");
        }
    }

}
