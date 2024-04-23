package com.example.cs360project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditAddItem extends AppCompatActivity {
    EditText name, quantity;
    Button save;

    itemsDB Idb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_add_item);

        name = findViewById(R.id.iName);
        quantity = findViewById(R.id.iQuantity);
        save = findViewById(R.id.isave);
        Idb = new itemsDB(this);


//      ADD ITEMS TO DATABASE
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String quantityText = quantity.getText().toString();
                Boolean savedata = Idb.saveitemdata(nameText, quantityText);
                //IF EDIT TEXT FIELD IS EMPTY
                if(TextUtils.isEmpty(nameText) || TextUtils.isEmpty(quantityText)){
                    Toast.makeText(EditAddItem.this, "Add Name and Quantity", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    if(savedata==true){
                        Toast.makeText(EditAddItem.this, "Saved Item", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), DatabaseInformationGrid.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(EditAddItem.this, "Item already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}