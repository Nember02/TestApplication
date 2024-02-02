package com.example.testapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testapplication.Adapter.VacancyAdapter;
import com.example.testapplication.Domain.VacancyDomain;
import com.example.testapplication.R;
import com.example.testapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();

        Window window=MainActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.white));
    }

    private void initRecyclerView(){
        ArrayList<VacancyDomain> itemsArrayList=new ArrayList<>();

        itemsArrayList.add(new VacancyDomain("Isolation Crew", "Samsung",140000,4.6, "pic1"));
        itemsArrayList.add(new VacancyDomain("Cooking Crew", "Samsung",150000,4.8, "pic2"));
        itemsArrayList.add(new VacancyDomain("Cleaning Crew", "SHI",120000,4.2, "pic1"));

        binding.popularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        binding.popularView.setAdapter(new VacancyAdapter(itemsArrayList, this));
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);


        startActivity(intent);

    }
}