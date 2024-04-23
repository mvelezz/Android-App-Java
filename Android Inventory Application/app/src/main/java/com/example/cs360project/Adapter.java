package com.example.cs360project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//ADAPTER FOR RECYCLER VIEW
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    static Context context;
    static ArrayList<String> uname;
    static ArrayList<String> uquantity;
    LayoutInflater layoutInflater;

    public Adapter(Context context, ArrayList<String> uname, ArrayList<String> uquantity){
        this.context = context;
        this.uname = uname;
        this.uquantity = uquantity;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Item.setText(String.valueOf(uname.get(position)));
        holder.quantity.setText(String.valueOf(uquantity.get(position)));
    }

    @Override
    public int getItemCount() {
        return uname.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Item, quantity;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            Item = itemView.findViewById(R.id.itemName);
            quantity = itemView.findViewById(R.id.itemQuantity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, IndividualItemScreen.class);
                    intent.putExtra("names", uname.get(getAdapterPosition()));
                    intent.putExtra("quantity", uquantity.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
