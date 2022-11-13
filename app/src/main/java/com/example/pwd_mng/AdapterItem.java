package com.example.pwd_mng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder {

        TextView nombre, user;

        public ViewHolderItem(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreItem);
            user = itemView.findViewById(R.id.nombreUser);
        }

        public void asignarDatos(ListItem item) {

            nombre.setText(item.getNombre());
            user.setText(item.getUser());

        }
    }
}
