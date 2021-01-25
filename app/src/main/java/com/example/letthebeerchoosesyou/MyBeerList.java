package com.example.letthebeerchoosesyou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBeerList extends Activity implements ItemClickListener{

    private RecyclerView recView;
    private RecyclerAdapter adapter;
    protected LinearLayoutManager layoutManager;
    private List<Data> dataList;
    private List<String> dataToSave;
    private List<Data> dataToGet;
    private static String fileName = "beerBucketList.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_beer_list);
        dataList = (ArrayList<Data>) getIntent().getSerializableExtra("dataList");
        setUpRecyclerView();
        if (dataList != null) {
            setUpRecyclerData();
        }
    }

    private void setUpRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true); //for adding new to the bottom

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(this);
        recView.setAdapter(adapter);
    }

    private void setUpRecyclerData() {
        adapter.addData(dataList);
    }

    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position, List<Data> data) {
        adapter.removeCell(position);
    }

    
}