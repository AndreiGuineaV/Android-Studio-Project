package com.example.carsshowsbookssports.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carsshowsbookssports.R;
import com.example.carsshowsbookssports.models.Car;

public class AddCustomCarActivity extends AppCompatActivity {

    private EditText editName, editBrand, editYear, editDescription;
    private ImageView imagePreview;
    private Button btnPickImage, btnSave;

    private Uri selectedImageUri = null;

    ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_car);

        editName = findViewById(R.id.editName);
        editBrand = findViewById(R.id.editBrand);
        editYear = findViewById(R.id.editYear);
        editDescription = findViewById(R.id.editDescription);
        imagePreview = findViewById(R.id.imagePreview);
        btnPickImage = findViewById(R.id.btnPickImage);
        btnSave = findViewById(R.id.btnSaveCar);

        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        imagePreview.setImageURI(selectedImageUri);
                    }
                }
        );

        btnPickImage.setOnClickListener(v -> openImagePicker());

        btnSave.setOnClickListener(v -> saveCar());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }

    private void saveCar() {
        String name = editName.getText().toString();
        String brand = editBrand.getText().toString();
        String year = editYear.getText().toString();
        String description = editDescription.getText().toString();

        if (name.isEmpty() || brand.isEmpty() || year.isEmpty() || description.isEmpty() || selectedImageUri == null) {
            Toast.makeText(this, "Complete all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        Car customCar = new Car(
                name,
                brand,
                Integer.parseInt(year),
                selectedImageUri.toString(),
                description
        );

        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedCar", (Parcelable) customCar);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
