package com.example.webclues;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment=new RegFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame,fragment).commit();
    }
}
