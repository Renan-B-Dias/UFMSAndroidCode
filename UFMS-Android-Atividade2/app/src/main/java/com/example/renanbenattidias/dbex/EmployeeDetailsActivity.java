package com.example.renanbenattidias.dbex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class EmployeeDetailsActivity extends BaseActivity {

    private TextView title;
    private EditText nameEditText;
    private EditText salaryEditText;
    private Button saveButton;
    private Button deleteButton;

    private Spinner positionsSpinner;

    private Employee employee;
    private List<Position> positionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        layoutReference = R.layout.activity_employee_details;
        super.onCreate(savedInstanceState);
        employee = (Employee) getIntent().getSerializableExtra("employee");
        nameEditText.setText(employee.name);
        salaryEditText.setText(employee.salary.toString());
        setUpOnClickListeners();

        setUpSpinner();
    }

    private void setUpSpinner() {
        positionsList = getPositions();

        String[] stringArray = new String[positionsList.size()];

        for (int index = 0; index < stringArray.length; index++)
            stringArray[index] = positionsList.get(index).name;

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, stringArray);
        positionsSpinner.setAdapter(adapter);
        int currentOffice = 0;


        if(employee.position != null) {
            String positionName = employee.position.name;
            for (int index = 0; index < positionsList.size(); index++) {
                if (positionsList.get(index).name.equals(positionName )) {
                    currentOffice = index;
                    break;
                }
            }
        }

        positionsSpinner.setSelection(currentOffice);
    }

    private List<Position> getPositions() {
        PositionDataBase database = new DBHelper(this);
        return database.getPositions();
    }

    @Override
    public void findViews() {
        title = findViewById(R.id.employeeDetailsTitle);
        nameEditText = findViewById(R.id.employeeDetailsNameTextField);
        salaryEditText = findViewById(R.id.employeeDetailsSalaryTextField);
        saveButton = findViewById(R.id.employeeDetailsSaveButton);
        deleteButton = findViewById(R.id.employeeDetailsDeleteButton);
        positionsSpinner = findViewById(R.id.positionsSpinner);
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
        return !nameEditText.getText().toString().isEmpty() &&
                !salaryEditText.getText().toString().isEmpty();
    }

    private void delete() {
        EmployeeDataBase database = new DBHelper(this);
        if(database.deleteEmployee(employee.id)) {
            finish();
        } else {
            showErrorToastWith("Something went wrong deleting!!");
        }
    }

    private void edit() {
        String newName = nameEditText.getText().toString();
        String salary = salaryEditText.getText().toString();
        Double doubleSalary = Double.parseDouble(salary);
        int selectedSpinerIndex = positionsSpinner.getSelectedItemPosition();
        Position position = positionsList.get(selectedSpinerIndex);

        employee.name = newName;
        employee.salary = doubleSalary;
        employee.position = position;

        EmployeeDataBase database = new DBHelper(this);
        if(database.update(employee)) {
            finish();
        } else {
            showErrorToastWith("Something went wrong editing!!");
        }
    }
}
