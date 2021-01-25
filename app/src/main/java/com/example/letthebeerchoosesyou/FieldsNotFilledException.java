package com.example.letthebeerchoosesyou;
import android.widget.Toast;

public class FieldsNotFilledException extends Exception {

    public FieldsNotFilledException (String message) {
        super(message);
    }

}
