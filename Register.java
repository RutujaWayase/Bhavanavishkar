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

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextFullName;
    EditText mTextPassword;
    EditText mTextPhoneno;
    EditText mTextConfrimPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    Button mButtonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Register");

        db = new DatabaseHelper(this);
        mTextFullName = (EditText)findViewById(R.id.FullName);
        mTextPassword = (EditText)findViewById(R.id.NumberPassword1);
        mTextPhoneno = (EditText)findViewById(R.id.Phone1);
        mTextConfrimPassword = (EditText)findViewById(R.id.NumberPassword2);
        mButtonRegister = (Button) findViewById(R.id.Register);
        mTextViewLogin = (TextView) findViewById(R.id.LogIn);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(Register.this,Login.class);
                startActivity(LoginIntent);

            }
        });


        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextFullName.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String phone1 = mTextPhoneno.getText().toString().trim();
                String cnf_pwd = mTextConfrimPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd))
                {
                    long val =  db.addUser(user, pwd);


                        Toast.makeText(Register.this, "You have Registered ", Toast.LENGTH_SHORT).show() ;
                        Intent moveToLogIn = new Intent( Register.this, Login.class);
                        startActivity(moveToLogIn);



                }
                else
                {
                    Toast.makeText(Register.this, "Password is not matching ", Toast.LENGTH_SHORT).show() ;
                }
            }
        });

    }
}