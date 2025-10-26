package com.example.carsshowsbookssports.adapters;

import android.content.Context;
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
        holder.imageCar.setImageResource(car.getImageResId());
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
