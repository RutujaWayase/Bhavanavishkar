package com.example.besafe_letsexplore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.besafe_letsexplore.dummy.DatabaseHelper;

public class Login extends AppCompatActivity {

    EditText mTextFullName;
    EditText mnumericPassword;
    EditText mTextPassword;
    Button mButtonLogIn;
    TextView mTextViewRegister;
    Button mButtonRegister;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");


        db = new DatabaseHelper(this);
        mTextFullName = (EditText)findViewById(R.id.FullName);
        mnumericPassword = (EditText)findViewById(R.id.NumberPassword1);
        mButtonLogIn = (Button) findViewById(R.id.LogIn);
        mTextViewRegister = (TextView) findViewById(R.id.Register);
        mButtonRegister = (Button) findViewById(R.id.Register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this,Register.class);
                startActivity(registerIntent);
            }
        });


        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextFullName.getText().toString().trim();
                String pwd = mnumericPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if (res == true)
                {
                    Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show() ;

                }
                else
                {
                    Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show() ;
                }
            }
        });
    }


    public void btn_Register(View view) {
        startActivity(new Intent(getApplicationContext(), Register.class));


    }
}