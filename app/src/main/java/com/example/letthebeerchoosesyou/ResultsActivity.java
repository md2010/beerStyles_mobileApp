package com.example.letthebeerchoosesyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultsActivity extends Activity implements ItemClickListener{

    private RecyclerView recView;
    private RecyclerResultsAdapter adapter;
    protected LinearLayoutManager layoutManager;
    private ArrayList<Float> queryParams;
    public List<Data> bucketList = new ArrayList<>();
    Call<List<Data>> apiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setUpRecyclerView();
        queryParams = (ArrayList<Float>) getIntent(). getSerializableExtra("queryParams");
        setUpApiCall();
    }

    private void setUpRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true); //for adding new to the bottom

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(layoutManager);
        adapter = new RecyclerResultsAdapter(this);
        recView.setAdapter(adapter);
    }

    private void setUpApiCall() {
        apiCall = NetworkUtils.getGetAPIInterface().getBeersWithAllParams
                (queryParams.get(0), queryParams.get(1), queryParams.get(2));
        apiCall.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful() && response.body() != null){
                    showProducts(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) { }
        });
    }

    private void showProducts(List<Data> beers) {
        adapter.addData(beers);
        adapter.notifyDataSetChanged();
    }

    public void launchMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("dataList", (Serializable) bucketList);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null)
            apiCall.cancel();
    }

    @Override
    public void onItemClick(int position, List<Data> data) {
        bucketList.add(data.get(position));
        Toast.makeText(this,"Beer added to your Beer Bucket List!",
                Toast.LENGTH_LONG).show();
    }
}