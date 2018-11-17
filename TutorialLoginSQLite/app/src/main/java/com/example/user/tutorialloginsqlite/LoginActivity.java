package com.example.user.tutorialloginsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // membuat variabel
    EditText username, password;
    Button btn_login;
    TextView btn_register;
    Intent i;
    DataHelperLogin dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DataHelperLogin(this); // instansiasi DataHelperLogin

        username = findViewById(R.id.editTextUsername); // untuk mengambil komponen dari view sesuai id yg sudah dibuat
        password = findViewById(R.id.editTextPassword);

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), RegisterActivity.class); // untuk pindah activity
                startActivity(i);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fungsi ketika tombol login di klik
                String user = username.getText().toString(); // untuk mengamnnil inputan yg sudah diinputkan
                String pass = password.getText().toString();
                Boolean login = dbHelper.checkLogin(user,pass); // memanggil fungsi checkLogin

                if (user.equals("") || pass.equals("")){ // jika username atau password kosong hasilnya field empty
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_LONG).show();
                } else {
                    if (login == true) { // jika login true maka akan pindah ke halaman MainAcitivty
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("username", user); // untuk passing data username
                        startActivity(i);
                        finish();
                    }else { // jika salah maaka keluar text username or password wrong
                        Toast.makeText(getApplicationContext(), "Username or password wrong!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
