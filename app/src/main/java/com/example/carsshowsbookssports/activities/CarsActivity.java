package com.example.carsshowsbookssports.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsshowsbookssports.R;
import com.example.carsshowsbookssports.adapters.CarAdapter;
import com.example.carsshowsbookssports.models.Car;
import com.example.carsshowsbookssports.utils.CarStorage;

import java.util.ArrayList;

public class CarsActivity extends AppCompatActivity {

    RecyclerView recyclerViewCars;
    CarAdapter carAdapter;
    ArrayList<Car> carList;
    Button btnAddCar;
    Button btnBackToMenu;
    Button addCustomCarBtn;

    private ActivityResultLauncher<Intent> addCarLauncher;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        recyclerViewCars = findViewById(R.id.recyclerViewCars);
        btnAddCar = findViewById(R.id.btnAddCar);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);
        addCustomCarBtn = findViewById(R.id.buttonAddCustomCar);

        carList = CarStorage.loadCars(this);
        //carList.add(new Car("Model S", "Tesla", 2023, R.drawable.tesla));
        //carList.add(new Car("Mustang", "Ford", 1977, R.drawable.mustang));
        //carList.add(new Car("Civic type R", "Honda", 2021, R.drawable.civic));

        carAdapter = new CarAdapter(this, carList, (car, v) ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(car.getName());

            String[] options = {"About", "Remove"};

            builder.setItems(options, (dialog, which) -> {
                if(which == 0)
                    showCarAboutDialog(car);
                else
                    removeCar(car);
            });
            AlertDialog dialog = builder.create();
            dialog.setOnDismissListener(d -> v.setBackgroundColor(Color.TRANSPARENT));

            dialog.show();
        });

        recyclerViewCars.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCars.setAdapter(carAdapter);

        addCarLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Car newCar;

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                            // API 33+
                            newCar = result.getData().getParcelableExtra("selectedCar", Car.class);
                        } else {
                            // API < 33
                            newCar = result.getData().getParcelableExtra("selectedCar");
                        }
                        if (newCar != null) {
                            carList.add(newCar);
                            carAdapter.notifyItemInserted(carList.size() - 1);

                            CarStorage.saveCars(this, carList);
                        }
                    }
                }
        );

        btnAddCar.setOnClickListener(v -> {
            Intent intent = new Intent(CarsActivity.this, AddCarActivity.class);
            intent.putExtra("addedCars", new ArrayList<>(carList));
            addCarLauncher.launch(intent);
        });

        btnBackToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(CarsActivity.this, MainActivity.class);
            startActivity(intent);
        });


        addCustomCarBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CarsActivity.this, AddCustomCarActivity.class);
            addCarLauncher.launch(intent); // folosești același launcher pe care îl ai deja
        });
    }

    private void showCarAboutDialog(Car car){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About " + car.getName());

        String details = car.getDescription();

        builder.setMessage(details);
        builder.setPositiveButton("Close", ((dialog, which) -> dialog.dismiss()));
        builder.show();
    }

    private void removeCar(Car car)
    {
        int position = carList.indexOf(car);
        if(position != -1)
        {
            carList.remove(position);
            carAdapter.notifyItemRemoved(position);
            CarStorage.saveCars(this, carList);

            Toast.makeText(this, car.getName() + " removed", Toast.LENGTH_SHORT).show();
        }
    }
}
