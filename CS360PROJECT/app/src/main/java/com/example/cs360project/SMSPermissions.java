package com.example.cs360project;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.telephony.SmsManager;

public class SMSPermissions extends AppCompatActivity {

    //private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    EditText phoneNumber;
    String msg, phoneNo;
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smspermissions);

        phoneNumber = findViewById(R.id.editTextText2);
        send = findViewById(R.id.signupbutton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SMSPermissions.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                {
                    sendSMS();
                }
                else
                {
                    ActivityCompat.requestPermissions(SMSPermissions.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
                }

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Thanks for signing up", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSMS() {
        String phone = phoneNumber.getText().toString();
        String message = "Item is out of stock";

        if (!phone.isEmpty())
        {
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phone, null, message, null, null);
            Toast.makeText(this, "Number has been initialized", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }
    public void sender(){
        String message = "Item is out of stock";
        SmsManager smsManager= SmsManager.getDefault();

        smsManager.sendTextMessage("1234", null, message, null, null);
        Toast.makeText(this, "Inventory has been depleted", Toast.LENGTH_SHORT).show();
    }
}