package com.example.letthebeerchoosesyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PollActivity extends Activity {

    public EditText etABV;
    private EditText etSRM;
    private EditText etIBU;
    private int numberOfQueryParams = 3;
    static List<Float> queryParams;
    private int countFieldsFilled = 0;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        etABV = findViewById(R.id.etABV);
        etSRM = findViewById(R.id.etSRM);
        etIBU = findViewById(R.id.etIBU);
        queryParams = new ArrayList<>();
    }

    public void getQueryParams() {
        ArrayList<String> inputValues = new ArrayList<>();
        inputValues.add(etABV.getText().toString());
        inputValues.add(etIBU.getText().toString());
        inputValues.add(etSRM.getText().toString());
        for (int i = 0; i < numberOfQueryParams; i++) {
            if (inputValues.get(i).equals(""))
                queryParams.add(404f);
            else {
                queryParams.add(Float.parseFloat(inputValues.get(i)));
                countFieldsFilled++;
            }
        }
    }

    public void launchResultsActivity(View view) {
        getQueryParams();
        validator = new Validator(queryParams);
        if ( !validator.validateInputFilled(countFieldsFilled)) {
            Toast.makeText(this, "ERROR: At least one field must be filled!",
                    Toast.LENGTH_LONG).show();
        } else if ( !validator.validateInputValue()) {
            Toast.makeText(this, "ERROR: Invalid input value!",
                    Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("queryParams", (Serializable) queryParams);
        startActivity(intent);
        clearFields();
    }

    private void clearFields() {
        etIBU.setText("");
        etSRM.setText("");
        etABV.setText("");
    }

}