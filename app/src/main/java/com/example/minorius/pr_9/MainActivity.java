package com.example.minorius.pr_9;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public ImageView main_1;
    public ImageView main_2;
    public ImageView main_3;
    public ImageView main_4;
    public ImageView main_5;

    ImageView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_1 = (ImageView) findViewById(R.id.main_1);
        main_2 = (ImageView) findViewById(R.id.main_2);
        main_3 = (ImageView) findViewById(R.id.main_3);
        main_4 = (ImageView) findViewById(R.id.main_4);
        main_5 = (ImageView) findViewById(R.id.main_5);

        main_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Category_activity.class);
                startActivity(i);
            }
        });
        main_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        main_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        main_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        main_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}