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

public class MainActivity extends AppCompatActivity {

    Button button;
    Button registerButton;

    EditText username;
    EditText password;

    TextView mainText;
    dbConnectLogin sqLiteDatabase = new dbConnectLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        button = findViewById(R.id.button);
        registerButton = findViewById(R.id.button2);
        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextTextPassword);
        mainText = findViewById(R.id.textView);

        //LOGIN VERIFICATION
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struser = username.toString();
                String strpass = password.toString();
                if(TextUtils.isEmpty(struser) || TextUtils.isEmpty(strpass)){
                    Toast.makeText(MainActivity.this, "Add username and password", Toast.LENGTH_SHORT).show();
                }
                boolean isLoggedID = sqLiteDatabase.checkUser(username.getText().toString(), password.getText().toString());
                if(isLoggedID){
                    Intent intent = new Intent(MainActivity.this, DatabaseInformationGrid.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                }


            }

        });

        //GET TO REGISTER SCREENf
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistrationScreen.class);
                startActivity(intent);



            }

        });



    }
}