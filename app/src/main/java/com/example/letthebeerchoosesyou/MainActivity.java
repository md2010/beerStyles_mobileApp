package com.example.letthebeerchoosesyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Data> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = (ArrayList<Data>) getIntent().getSerializableExtra("dataList");
    }

    public void launchPollActivity(View view) {
        Intent intent = new Intent(this, PollActivity.class);
        startActivity(intent);
    }

    public void launchBeerList(View view) {
        Intent intent = new Intent(this, MyBeerList.class);
        intent.putExtra("dataList", (Serializable) dataList);
        startActivity(intent);
    }
}