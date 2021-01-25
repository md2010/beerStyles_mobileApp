package com.example.letthebeerchoosesyou;

import android.widget.Toast;

import java.util.List;

public class Validator {

    private float abv;
    private float srm;
    private float ibu;

    public Validator (List<Float> params) {
        this.abv = params.get(0);
        this.srm = params.get(1);
        this.ibu = params.get(2);
    }

    public boolean validateInputFilled (int counter) {
        if (counter  < 1) {
           return false;
        } return true;
    }

    public boolean validateInputValue() {
        if (( abv < 2 || abv > 13 && abv != 404) ||
            ( srm < 2 || srm > 40 && srm != 404) ||
            ( ibu < 0 || ibu > 100 && ibu != 404) ) {
           return false;
        } return true;
    }

}
