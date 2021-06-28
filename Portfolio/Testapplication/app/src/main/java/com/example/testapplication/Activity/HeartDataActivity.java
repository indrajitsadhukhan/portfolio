package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testapplication.R;

import butterknife.ButterKnife;

public class HeartDataActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_data);
        ButterKnife.bind(this);
    }




}