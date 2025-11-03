package com.example.carsshowsbookssports.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsshowsbookssports.R;
import com.example.carsshowsbookssports.adapters.AddCarAdapter;
import com.example.carsshowsbookssports.models.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddCarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button confirmAddButton;
    private Button backToCarsButton;
    private AddCarAdapter addCarAdapter;
    private List<Car> availableCars;
    private Car selectedCar = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        recyclerView = findViewById(R.id.recyclerViewAvailableCars);
        confirmAddButton = findViewById(R.id.buttonConfirmAdd);
        backToCarsButton = findViewById(R.id.buttonBacktoCars);

        availableCars = getAvailableCars();
        // primim lista mașinilor deja adăugate
        List<Car> addedCars = (List<Car>) getIntent().getSerializableExtra("addedCars", ArrayList.class);

        // eliminăm din availableCars pe cele deja adăugate
        if (addedCars != null) {
                availableCars.removeIf(car -> addedCars.stream().anyMatch(added -> added.getName().equals(car.getName())));
        }


        addCarAdapter = new AddCarAdapter(this, availableCars, car -> {
            selectedCar = car;
            Toast.makeText(this, "Selectat: " + car.getName(), Toast.LENGTH_SHORT).show();
        });
        recyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this));
        recyclerView.setAdapter(addCarAdapter);

        confirmAddButton.setOnClickListener(v -> {
            if (selectedCar != null) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedCar", (Serializable) selectedCar);
                setResult(RESULT_OK, resultIntent);
                finish(); // închide AddCarActivity

            } else {
                Toast.makeText(this, "Selectează o mașină înainte de a adăuga!", Toast.LENGTH_SHORT).show();
            }
        });

        backToCarsButton.setOnClickListener(v -> {
            finish();
        });

    }
    private List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Model S", "Tesla", 2023, R.drawable.tesla));
        cars.add(new Car("Mustang", "Ford", 2022, R.drawable.mustang));
        cars.add(new Car("Civic", "Honda", 2021, R.drawable.civic));
        cars.add(new Car("Supra", "Toyota", 2020, R.drawable.supra));
        return cars;
    }
}
