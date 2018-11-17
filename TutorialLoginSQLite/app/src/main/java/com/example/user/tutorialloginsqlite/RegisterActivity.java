package com.example.user.tutorialloginsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //buat variable
    DataHelperLogin dbHelper;
    Button btn_register;
    EditText username, password, retypepassword;
    String user, pass, retypepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DataHelperLogin(this);

        username = (EditText) findViewById(R.id.editTextUsername); // untuk mengambil komponen dari view sesuai id yg dibuat
        password = (EditText) findViewById(R.id.editTextPassword);
        retypepassword = (EditText) findViewById(R.id.editTextPasswordRetype);

        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // fungsi ketika tombol register di klik
                user = username.getText().toString(); // untuk mengambil inputan yg sudah diinputkan
                pass = password.getText().toString();
                retypepass = retypepassword.getText().toString();

                //jika username/password/retype password kosong maka hasilnya memunculkan text fields empty
                if (user.equals("") || pass.equals("") || retypepass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_LONG).show();
                } else {

                    // jika password sama dengan retype password maka akan memunculkan text username sudah ada
                    if (pass.equals(retypepass)) {
                        Boolean userExist = dbHelper.insert(user, pass);

                        if (userExist == true) {
                            Boolean insert = dbHelper.insert(user, pass);
                            // jika insert benar akan memunculkan text registrasi berhasil dan pindah ke activity login
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registration Success!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Username already Exist!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password doesn't match!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}



