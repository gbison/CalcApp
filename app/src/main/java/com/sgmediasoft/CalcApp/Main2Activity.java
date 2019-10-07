package com.sgmediasoft.CalcApp;
/*
    Description: Designed to provide basic input/output of normal arithamtic operations.
    Date: 9/2/2019
    Class: CIT238 (SKCTCS)
    Author: George Ison
 */
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    GridView grid;
    MainGridAdapter gridAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        /* //Use this code to pass data around from one activity to another.
            String passedData = getIntent().getStringExtra("value1");
            TextView otherText = findViewById(R.id.textID);
            otherText.setText(passedData);
         */

        gridAdapter = new MainGridAdapter(this);
        grid = findViewById(R.id.mainGrid);
        ImageView pic= findViewById(R.id.imgLarge);
        grid.setAdapter(gridAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext( ), "Selected Species " + (position + 1), Toast.LENGTH_SHORT).show( );
                pic.setImageResource(gridAdapter.Animals[position]);
            }
        });

    }
}
