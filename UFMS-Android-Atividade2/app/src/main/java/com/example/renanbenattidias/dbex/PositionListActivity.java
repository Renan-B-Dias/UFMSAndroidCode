package com.example.renanbenattidias.dbex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PositionListActivity extends AppCompatActivity {

    ListView positionsListView;

    private List<Position> positions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_list);
        findViews();
        positions = getPositions();
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
        String[] positionArray = new String[] {};
        positions = getPositions();
        List<String> positionList = new ArrayList<>();

        for(Position position: positions)
            positionList.add(position.name);

        String[] array = positionList.toArray(positionArray);

        ArrayAdapter adatper = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, array);

        positionsListView.setAdapter(adatper);
    }

    private void findViews() {
        positionsListView = findViewById(R.id.positionsListView);
    }

    private void setUpListView() {
        String[] positionArray = new String[] {};   // Arrays don't grow in Java... --'
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
                goToDetails(positions.get(i));
            }
        });
    }

    private void goToDetails(Position position) {
        Intent intent = new Intent(this, PositionDetailsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private List<Position> getPositions() {
        DBHelper database = new DBHelper(this);
        List<Position> positions = database.getPositions();
        database.close();
        return positions;
    }
}
