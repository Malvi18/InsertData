package com.example.webclues.JsonExample;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.webclues.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Fragment fragment=new BlankFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_layout,fragment).commit();
    }
}
