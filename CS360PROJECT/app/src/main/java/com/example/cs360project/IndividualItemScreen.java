package com.example.cs360project;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Currency;

public class IndividualItemScreen extends AppCompatActivity {
    EditText name, quantity;
    Button delete, editsave;
    itemsDB db;

    TextView count;

    SMSPermissions sms;

    ArrayList<String> uquantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_individual_item_screen);

        name = findViewById(R.id.editName);
        quantity = findViewById(R.id.editQuantity);
        delete = findViewById(R.id.delete);
        editsave = findViewById(R.id.editsave);
        db = new itemsDB(this);
        uquantity = new ArrayList<>();

        Intent intent = getIntent();

        name.setText(intent.getStringExtra("names"));
        quantity.setText(intent.getStringExtra("quantity"));


        //DELETE ITEM
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nameText = name.getText().toString();
                Boolean deletedata = db.deleteitemdata(nameText);

                    if(deletedata==true) {
                        Toast.makeText(IndividualItemScreen.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DatabaseInformationGrid.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(IndividualItemScreen.this, "Item was NOT Deleted", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        //EDIT QUANTITY OF ITEM
        editsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTEXT = name.getText().toString();
                String quantityTEXT = quantity.getText().toString();
                Boolean updateitemdata = db.updateitemdata(nameTEXT, quantityTEXT);
                Intent intent1 = getIntent();

                if(updateitemdata==true){
                    Toast.makeText(IndividualItemScreen.this, "Item Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), DatabaseInformationGrid.class);
                    if (quantity.getText().toString() == "0")
                    {
                        sms.sender();
                        if (ContextCompat.checkSelfPermission(IndividualItemScreen.this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED)
                        {
                            sms.sender();
                            startActivity(intent);
                        }
                        else
                        {
                            ActivityCompat.requestPermissions(IndividualItemScreen.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
                        }


                    }
                    else
                    {
                        startActivity(intent);
                    }

                }
                else{
                    Toast.makeText(IndividualItemScreen.this, "Item was NOT Updated", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

}