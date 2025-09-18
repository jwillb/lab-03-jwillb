package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    private boolean editCity = false;
    private int editIndex = -1;

    @Override
    public void addCity(City city) {
        if (!editCity) {
            cityAdapter.add(city);
        } else {
            City cityToEdit = dataList.get(editIndex);
            cityToEdit.setName(city.getName());
            cityToEdit.setProvince(city.getProvince());
            editCity = false;
            editIndex = -1;
        }
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] cities = { "Edmonton", "Vancouver", "Toronto" };
        String[] provinces = { "AB", "BC", "ON" };

        dataList = new ArrayList<City>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });
        cityList.setOnItemClickListener((parent, view, index, id) -> {
            editCity = true;
            editIndex = index;
            new AddCityFragment().show(getSupportFragmentManager(), "Edit City");
        });
    }
}