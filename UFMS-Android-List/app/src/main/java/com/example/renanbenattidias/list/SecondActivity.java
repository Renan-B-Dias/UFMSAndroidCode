package com.example.renanbenattidias.list;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

//    private final ArrayList<String> cities = generateCities();

    TextView nameTextView;
    TextView cityTextView;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViews();
        populateList();
        String name = getIntent().getExtras().getString("name");
        applyLayout(name);
    }

    private void applyLayout(String name) {
        nameTextView.setText(name);
    }

    private void findViews() {
        nameTextView = findViewById(R.id.name2TextView);
        cityTextView = findViewById(R.id.city2TextView);
        listView = findViewById(R.id.cityListView);
    }

    private void populateList() {
        Resources resources = getResources();
        final String[] cities = resources.getStringArray(R.array.cities_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_checked,
                android.R.id.text1, cities);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View city, int index, long l) {
                String cityName = cities[index];
                cityTextView.setText(cityName);
            }
        });


        // Tuesday
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View city, int i, long l) {
//                String cityName = cities.get(i);
//                cityTextView.setText(cityName);
//            }
//        });
    }

//    private ArrayList<String> generateCities() {
//        final ArrayList<String> cities = new ArrayList<String>();
//        cities.add("Iran of the Pillars");
//        cities.add("El Dorado");
//        cities.add("Shangri La");
//        cities.add("Libertalia");
//        cities.add("Arendelle");
//        cities.add("Tifeti");
//        cities.add("San Fransokyo");
//        return cities;
//    }
}
