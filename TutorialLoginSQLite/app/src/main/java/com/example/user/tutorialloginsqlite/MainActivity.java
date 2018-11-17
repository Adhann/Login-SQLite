package com.example.user.tutorialloginsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //buat varaiabel
    Intent i;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = findViewById(R.id.welcome); // untuk mengambil komponen dari view sesuai id yg dibuat
        Intent i = getIntent(); // mengambil intent

        //memunculkan text Welcome dan mengambil string extra username
        welcome.setText("Welcome, \n" + i.getStringExtra("username"));
    }
}
