package com.example.cs360project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class RegistrationScreen extends AppCompatActivity {
    EditText edtUserName, edtPassword;
    Button registerButton, loginButton;

    TextView txtInfo;

    dbConnectLogin sqLiteDatabase = new dbConnectLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration_screen);

        edtUserName = findViewById(R.id.newUserName);
        edtPassword = findViewById(R.id.newPassword);
        registerButton = findViewById(R.id.registerButton);
        txtInfo = findViewById(R.id.textViewRegister);
        loginButton = findViewById(R.id.loginOnReg);


        //SEND BACK TO LOGIN SCREEN
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //ADD USER TO DATABASE
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = edtUserName.getText().toString();
                String strPassword = edtPassword.getText().toString();

                Boolean savedata = sqLiteDatabase.saveuserdata(strUsername, strPassword);
                if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strPassword)){
                    Toast.makeText(RegistrationScreen.this, "Add username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    if(savedata==true){
                        Toast.makeText(RegistrationScreen.this, "Save Contact", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(RegistrationScreen.this, "Contact Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}