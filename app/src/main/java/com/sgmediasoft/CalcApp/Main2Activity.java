package com.sgmediasoft.CalcApp;
/*
    Description: Designed to provide basic input/output of normal arithamtic operations.
    Date: 9/2/2019
    Class: CIT238 (SKCTCS)
    Author: George Ison
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String passedData = getIntent().getStringExtra("value1");
        TextView otherText = findViewById(R.id.textID);
        otherText.setText(passedData);
    }
}
