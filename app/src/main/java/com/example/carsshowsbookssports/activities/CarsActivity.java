package com.example.carsshowsbookssports.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsshowsbookssports.R;
import com.example.carsshowsbookssports.adapters.CarAdapter;
import com.example.carsshowsbookssports.models.Car;

import java.util.ArrayList;
import java.util.List;

public class CarsActivity extends AppCompatActivity {

    RecyclerView recyclerViewCars;
    CarAdapter carAdapter;
    List<Car> carList;
    Button btnAddCar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        recyclerViewCars = findViewById(R.id.recyclerViewCars);
        btnAddCar = findViewById(R.id.btnAddCar);

        carList = new ArrayList<>();
        carList.add(new Car("Model S", "Tesla", 2023, R.drawable.tesla));
        carList.add(new Car("Mustang", "Ford", 1977, R.drawable.mustang));
        carList.add(new Car("Civic type R", "Honda", 2021, R.drawable.civic));

        carAdapter = new CarAdapter(this, carList);
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCars.setAdapter(carAdapter);

        btnAddCar.setOnClickListener(v -> {
            carList.add(new Car("Supra", "Toyota", 1994, R.drawable.supra));
            carAdapter.notifyItemInserted(carList.size() -1);
            recyclerViewCars.scrollToPosition(carList.size()-1);
        });
    }
}
