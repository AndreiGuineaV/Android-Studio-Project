package com.example.carsshowsbookssports.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
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
    Button btnBackToMenu;

    private ActivityResultLauncher<Intent> addCarLauncher;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        recyclerViewCars = findViewById(R.id.recyclerViewCars);
        btnAddCar = findViewById(R.id.btnAddCar);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);

        carList = new ArrayList<>();
        //carList.add(new Car("Model S", "Tesla", 2023, R.drawable.tesla));
        //carList.add(new Car("Mustang", "Ford", 1977, R.drawable.mustang));
        //carList.add(new Car("Civic type R", "Honda", 2021, R.drawable.civic));

        carAdapter = new CarAdapter(this, carList);
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCars.setAdapter(carAdapter);

        addCarLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Car newCar;

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                            // API 33+
                            newCar = result.getData().getSerializableExtra("selectedCar", Car.class);
                        } else {
                            // API < 33
                            newCar = (Car) result.getData().getSerializableExtra("selectedCar");
                        }
                        carList.add(newCar);
                        carAdapter.notifyItemInserted(carList.size() - 1);
                    }
                }
        );

        btnAddCar.setOnClickListener(v -> {
            Intent intent = new Intent(CarsActivity.this, AddCarActivity.class);
            addCarLauncher.launch(intent);
        });

        btnBackToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CarsActivity.this, MainActivity.class);
            startActivity(intent);
        });

    }

}
