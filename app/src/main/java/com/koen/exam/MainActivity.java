package com.koen.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import com.koen.exam.views.Impl.*;

public class MainActivity extends AppCompatActivity {
    DataSingleton dataSingleton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSingleton = DataSingleton.getInstance();
        initSharedPreferences();
        dataSingleton.initDataSharedPreferences();
        if (dataSingleton.jwtToken.equals("")){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            startActivity(new Intent(MainActivity.this, NavigationActivity.class));
        }

    }
    private void initSharedPreferences(){
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        dataSingleton.sharedPreferences = sharedPreferences;
    }
}