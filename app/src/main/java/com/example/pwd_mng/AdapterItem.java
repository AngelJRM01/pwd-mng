package com.example.pwd_mng;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterItem extends RecyclerView.Adapter<AdapterItem.ViewHolderItem> {

    private ArrayList<ListItem> listDatos;
    private Context context;
    private LayoutInflater inflater;

    public AdapterItem(ArrayList<ListItem> listDatos, Context context) {
        this.listDatos = listDatos;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AdapterItem.ViewHolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list, null);
        return new ViewHolderItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterItem.ViewHolderItem holder, int position) {
        holder.asignarDatos(listDatos.get(position));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ItemView.class);
                intent.putExtra("id", holder.id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView nombre, user;
        ImageView favorite;
        int id;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardItem);
            nombre = itemView.findViewById(R.id.nombreItem);
            user = itemView.findViewById(R.id.nombreUser);
            favorite = itemView.findViewById(R.id.favouriteItem);

        }

        public void asignarDatos(ListItem item) {

            nombre.setText(item.getNombre());
            user.setText(item.getUser());
            id = item.getId();
            if(item.getFavourite()){
                favorite.setImageResource(R.drawable.star_full);
            }

        }
    }
}
