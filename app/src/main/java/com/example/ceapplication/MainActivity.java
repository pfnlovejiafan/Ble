package com.example.ceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.ble.CynTest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int calculation = new CynTest().calculation(10);
        Log.e("sjdhjsdjsdh", "onCreate: "+calculation);
    }
}