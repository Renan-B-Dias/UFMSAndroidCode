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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectPositionActivity extends AppCompatActivity {

    TextView title;
    ListView positionsListView;

    private List<Position> positions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_position);
        findViews();
        applyLayout();
        positions = getPositions();
        setUpListView();
    }

    private void applyLayout() {
        title.setText("Select position");
    }

    private void findViews() {
        positionsListView = findViewById(R.id.selectPositionListView);
        title = findViewById(R.id.selectPositionTitle);
    }

    private void setUpListView() {
        String[] positionArray = new String[] {};
        List<String> positionList = new ArrayList<>();

        for(Position position: positions)
            positionList.add(position.name);

        String[] array = positionList.toArray(positionArray);

        ArrayAdapter adatper = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, array);

        positionsListView.setAdapter(adatper);

        positionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Position position = positions.get(i);
                if(position != null)
                    save(position);
                else
                    showErrorToastWith("Error getting position");
            }
        });
    }

    private void save(Position position) {
        String name = getIntent().getExtras().getString("name");
        String salary = getIntent().getExtras().getString("salary");

        Double doubleSalary = Double.parseDouble(salary);

        if(doubleSalary != null) {
            Employee employee = new Employee(name, position, doubleSalary);
            DBHelper database = new DBHelper(this);
            if(database.create(employee)) {
                Toast.makeText(this, "Employee saved", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

                finish();
            }
            else
                showErrorToastWith("Error creating employee");
        } else {
            showErrorToastWith("Error casting salary");
        }
    }

    private void showErrorToastWith(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private List<Position> getPositions() {
        DBHelper database = new DBHelper(this);
        List<Position> positions = database.getPositions();
        database.close();
        return positions;
    }
}
