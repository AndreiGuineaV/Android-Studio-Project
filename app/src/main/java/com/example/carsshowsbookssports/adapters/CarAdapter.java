package com.example.carsshowsbookssports.adapters;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsshowsbookssports.R;
import com.example.carsshowsbookssports.models.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private Context context;
    private List<Car> carList;

    public interface OnCarClickListener {
        void onCarClick(Car car, View v);
    }

    private OnCarClickListener listener;

    public CarAdapter(Context context, List<Car> carList, OnCarClickListener listener) {
        this.context = context;
        this.carList = carList;
        this.listener = listener;
    }
    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarAdapter.CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.textCarName.setText(car.getName());
        holder.textCarBrand.setText(car.getBrand());
        holder.textCarYear.setText(String.valueOf(car.getYear()));
        //holder.imageCar.setImageResource(car.getImageResId());
        if (car.getImageUri().startsWith("content")) {
            holder.imageCar.setImageURI(Uri.parse(car.getImageUri()));
        } else {
            holder.imageCar.setImageResource(Integer.parseInt(car.getImageUri()));
        }

        holder.itemView.setOnClickListener(v -> {
            v.setBackgroundColor(Color.LTGRAY);
            listener.onCarClick(car, v);
        });

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder{

        ImageView imageCar;
        TextView textCarName, textCarBrand, textCarYear;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCar = itemView.findViewById(R.id.imageCar);
            textCarName = itemView.findViewById(R.id.textCarName);
            textCarBrand = itemView.findViewById(R.id.textCarBrand);
            textCarYear = itemView.findViewById(R.id.textCarYear);
        }
    }
}
