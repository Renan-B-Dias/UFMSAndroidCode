package com.example.renanbenattidias.dbex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button listPositionsButton;
    Button addPositionsButton;
    Button listEmployeesButton;
    Button createEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        applyLayout();
        setUpOnClickListeners();
    }

    private void applyLayout() {
        listPositionsButton.setText("Positions");
        addPositionsButton.setText("Add Positions");
        listEmployeesButton.setText("Employees");
        createEmployeeButton.setText("Create Employee");
    }

    private void findViews() {
        listPositionsButton = findViewById(R.id.listPositionsButton);
        addPositionsButton = findViewById(R.id.addPositionButton);
        listEmployeesButton = findViewById(R.id.listEmployeesButton);
        createEmployeeButton = findViewById(R.id.mainCreateEmployeeButton);
    }

    private void setUpOnClickListeners() {
        listPositionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPositionsList();
            }
        });

        addPositionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goTAddoPosition();
            }
        });

        listEmployeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToListEmployees();
            }
        });

        createEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateEmployee();
            }
        });
    }

    private void goToPositionsList() {
        Intent positionsView = new Intent(this, PositionListActivity.class);
        startActivity(positionsView);
    }

    private void goTAddoPosition() {
        Intent positionsView = new Intent(this, CreatePositionActivity.class);
        startActivity(positionsView);
    }

    private void goToListEmployees() {
        Intent employeesList = new Intent(this, EmployeeListActivity.class);
        startActivity(employeesList);
    }

    private void goToCreateEmployee() {
        Intent createEmployeeView = new Intent(this, CreateEmployeeActivity.class);
        startActivity(createEmployeeView);
    }
}
