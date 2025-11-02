package com.example.carsshowsbookssports.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carsshowsbookssports.R;
import com.example.carsshowsbookssports.activities.AddCarActivity;
import com.example.carsshowsbookssports.models.Car;

import java.util.List;

public class AddCarAdapter extends RecyclerView.Adapter<AddCarAdapter.ViewHolder> {
    public interface OnCarSelectedListener {
        void onCarSelected(Car car);
    }

    private Context context;
    private List<Car> carList;
    private OnCarSelectedListener listener;
    private int selectedPosition = -1;

    public AddCarAdapter(Context context, List<Car> carList, OnCarSelectedListener listener) {
        this.context = context;
        this.carList = carList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public AddCarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_add_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddCarAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Car car = carList.get(position);
        holder.nameText.setText(car.getName());
        holder.brandText.setText(car.getBrand());
        holder.yearText.setText(String.valueOf(car.getYear()));
        holder.imageView.setImageResource(car.getImageResId());

        // fundal gri dacă e selectată
        if (position == selectedPosition) {
            holder.itemView.setBackgroundColor(Color.parseColor("#DDDDDD"));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position;
            listener.onCarSelected(car);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, brandText, yearText;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textName);
            brandText = itemView.findViewById(R.id.textBrand);
            yearText = itemView.findViewById(R.id.textYear);
            imageView = itemView.findViewById(R.id.imageViewCar);
        }
    }
}
