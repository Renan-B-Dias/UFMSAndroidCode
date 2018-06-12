package com.example.renanbenattidias.dbex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private ListView positionsListView;
    private TextView titleView;

    private List<Employee> employees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        findViews();
        applyLayout();
        employees = getEmployees();
        setUpListView();
    }

    // TODO Repeating action, refactor later...
    @Override
    protected void onResume() {
        super.onResume();
        reloadData();
    }

    // "Copied" from iOS
    private void reloadData() {
        employees = getEmployees();

        ArrayAdapter adatper = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, employees);

        positionsListView.setAdapter(adatper);
    }

    private void findViews() {
        positionsListView = findViewById(R.id.listEmployeeListView);
        titleView = findViewById(R.id.listEmployeeTitle);
    }

    private void applyLayout() {
        titleView.setText("Employees");
    }

    private void setUpListView() {

        ArrayAdapter adatper = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, employees);

        positionsListView.setAdapter(adatper);

        positionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToDetails(employees.get(i));
            }
        });
    }

    private void goToDetails(Employee employee) {
        Intent intent = new Intent(this, EmployeeDetailsActivity.class);
        intent.putExtra("employee", employee);
        startActivity(intent);
    }

    private List<Employee> getEmployees() {
        DBHelper database = new DBHelper(this);
        List<Employee> employees = database.getEmployees();
        database.close();
        return employees;
    }
//    }
}
