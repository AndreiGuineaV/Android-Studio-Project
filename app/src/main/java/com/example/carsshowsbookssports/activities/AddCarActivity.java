package com.example.carsshowsbookssports.activities;

import android.content.Intent;
import android.os.Build;
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
import com.example.carsshowsbookssports.utils.CarStorage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddCarActivity extends AppCompatActivity {

    //IDEE: preluarea informatiilor setate din toate categoriile si in functie de toate se calculeaza tipul
    //de persoana si ce goal-uri ai putea lua in vedere cu lucrurile pe care ti le doresti sa le faci
    //sau sa le obtii;

    //Task-uri ramase pentru masini:
        //mai multe masini in lista
        //adaugarea optiunii de a iti inregistra masina pe care o ai irl


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
        ArrayList<Car> addedCars = CarStorage.loadCars(this);
        //List<Car> addedCars = null;
       // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
       //     addedCars = (List<Car>) getIntent().getSerializableExtra("addedCars", ArrayList.class);
       // }
       // else
       //     addedCars = (ArrayList<Car>) getIntent().getSerializableExtra("addedCars");

        // eliminăm din availableCars pe cele deja adăugate
        if (addedCars != null) {
            List<Car> finalAddedCars = addedCars;
            availableCars.removeIf(car -> finalAddedCars.stream().anyMatch(added -> added.getName().equals(car.getName())));
        }


        addCarAdapter = new AddCarAdapter(this, availableCars, car -> {
            selectedCar = car;
            Toast.makeText(this, "Selected: " + car.getName(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Select a car before pressing the add car button!", Toast.LENGTH_SHORT).show();
            }
        });

        backToCarsButton.setOnClickListener(v -> {
            finish();
        });

    }
    private List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Model S", "Tesla", 2023, String.valueOf(R.drawable.tesla), "The 2023 Tesla Model S is a luxury electric sedan available in two trims, a base dual-motor(670hp) model and the Plaid(1020hp). It is known for its long electric range, high-performance acceleration, and advanced interior technology, which includes large touchscreen displays and a premium audio system. Key features include all-wheel drive, an adaptive air suspension, and a suite of standard safety features. "));
        cars.add(new Car("Mustang", "Ford", 1977, String.valueOf(R.drawable.mustang), "The 1977 Ford Mustang, primarily the Mustang II model, was a sporty coupe adapted for the era's emissions and safety regulations, focusing more on style and efficiency than raw power. Key features included sporty styling cues like the aggressive Cobra II package with unique decals and a lowered suspension, a range of engine options from a 2.3L four-cylinder to a 302 V8, and luxurious interior options like a tachometer and contoured bucket seats."));
        cars.add(new Car("Civic Type R", "Honda", 2021, String.valueOf(R.drawable.civic),"The 2021 Honda Civic Type R is a high-performance hatchback featuring a 306-horsepower 2.0-liter turbocharged engine and a 6-speed manual transmission. Key features include a 3-mode drive system (Comfort, Sport, +R), an adaptive damper system, and a helical limited-slip differential. It also has a Honda LogR datalogger, a specific exterior styling with a rear wing and triple-outlet exhaust, and interior amenities like high-bolstered sport seats and an Alcantara-wrapped steering wheel."));
        cars.add(new Car("Supra", "Toyota", 2002, String.valueOf(R.drawable.supra),"The Toyota Supra Mk4 is a Japanese sports car produced from 1993 to 2002, famous for its twin-turbocharged 3.0L 2JZ-GTE inline-six engine that delivers approximately 320 horsepower. It features a sleek, low-slung body, an advanced suspension, a driver-focused interior with a two-seater cockpit, and is well-regarded for its tuning potential, aerodynamic design, and iconic status in pop culture. "));
        return cars;
    }
}
