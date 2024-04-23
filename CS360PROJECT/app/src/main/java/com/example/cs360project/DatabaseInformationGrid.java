package com.example.cs360project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;

public class DatabaseInformationGrid extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton button;
    Button sms;
    itemsDB dbh;
    ArrayList<String> unames, uquantity;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_database_information_grid);

        recyclerView = findViewById(R.id.recycler);
        button = findViewById(R.id.floatingActionButton);
        dbh = new itemsDB(this);
        sms = findViewById(R.id.sms);


        //BUTTON TO EDIT ITEMS SCREEN
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseInformationGrid.this, EditAddItem.class);
                startActivity(intent);
            }
        });

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatabaseInformationGrid.this, SMSPermissions.class);
                startActivity(intent);
            }
        });

        unames = new ArrayList<>();
        uquantity = new ArrayList<>();

        adapter = new Adapter(DatabaseInformationGrid.this, unames, uquantity);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(DatabaseInformationGrid.this));

        displaydata();
    }

    //SHOW INVENTORY ITEMS
    private void displaydata() {
        Cursor cursor = dbh.getIdata();
        if (cursor.getCount()==-1){
            Toast.makeText(this, "No Item", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                unames.add(cursor.getString(0));
                uquantity.add(cursor.getString(1));
            }
        }
    }
}