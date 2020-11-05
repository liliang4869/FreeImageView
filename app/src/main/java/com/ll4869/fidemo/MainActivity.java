package com.ll4869.fidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ll4869.fidemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

    }
}