package com.example.mad_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button lg, sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lg = (Button) findViewById(R.id.login1);
        sign = (Button) findViewById(R.id.signup1);
        lg.setOnClickListener(MainActivity.this);
        sign.setOnClickListener(MainActivity.this);



    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login1: {
                startActivity(new Intent(MainActivity.this, frontpage.class));
                break;
            }
            case R.id.signup1:{
                startActivity(new Intent(MainActivity.this, register.class));

                break;
            }
            default:{}
        }

    }


    }
