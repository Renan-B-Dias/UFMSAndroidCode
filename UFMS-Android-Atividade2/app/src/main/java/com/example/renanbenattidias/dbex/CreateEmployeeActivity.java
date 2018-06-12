package com.example.renanbenattidias.dbex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEmployeeActivity extends AppCompatActivity {

    TextView titleTextView;
    EditText nameEditText;
    EditText salaryEditText;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_employee);
        findViews();
        applyLaoyout();
        setUpOnClickListeners();
    }

    private void findViews() {
        titleTextView = findViewById(R.id.createEmployeeTitle);
        nameEditText = findViewById(R.id.employeeNameTextField);
        salaryEditText = findViewById(R.id.employeeSalaryTextField);
        nextButton = findViewById(R.id.employeeNextbutton);
    }

    private void applyLaoyout() {
        titleTextView.setText("New Employee");
        nameEditText.setHint("Name");
        nameEditText.setText("");

        salaryEditText.setHint("Salary");
        salaryEditText.setText("");

        nextButton.setText("Next");
    }

    private void setUpOnClickListeners() {
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valid()) {
                    goToSelectPosition();
                } else {
                    showErrorToastWith("Error on creating");
                }
            }
        });
    }

    private boolean valid() {
        String name = nameEditText.getText().toString();
        String salary = salaryEditText.getText().toString();
        return (name != null && !name.isEmpty()) && (salary != null && !salary.isEmpty());
    }

    private void showErrorToastWith(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void goToSelectPosition() {
        String name = nameEditText.getText().toString();
        String salary = salaryEditText.getText().toString();

        Intent nextView = new Intent(this, SelectPositionActivity.class);
        nextView.putExtra("name", name);
        nextView.putExtra("salary", salary);
        startActivity(nextView);
    }
}
