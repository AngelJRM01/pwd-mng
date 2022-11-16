package com.example.pwd_mng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> listDatos;
    RecyclerView recycler;
    private ItemDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);


        dbHelper = new ItemDbHelper(getApplicationContext(), "pwd-mng.db");
        db = dbHelper.getWritableDatabase();

        listDatos = new ArrayList<>();

        buscarItems();

        AdapterItem adapterItem = new AdapterItem(listDatos, this);
        recycler = findViewById(R.id.itemList);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapterItem);

    }

    private SQLiteDatabase db;

    @SuppressLint("Range")
    private void buscarItems(){
        String[] columns = {
                ItemContract.ItemEntry._ID,
                ItemContract.ItemEntry.COLUMN_NAME_Name,
                ItemContract.ItemEntry.COLUMN_NAME_Username,
                ItemContract.ItemEntry.COLUMN_NAME_Favorite
        };
        Cursor cursor = db.query(ItemContract.ItemEntry.TABLE_NAME, columns, null, null, null, null, null);
        try {
            while (cursor.moveToNext()) {
                Item item = new Item(
                        cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry._ID)) ,
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Name)),
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Username)));

                item.setFavourite(cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Favorite)) == 1);

                listDatos.add(item);
            }
        } finally {
            cursor.close();
        }
    }

    public void changeActivityToCreate(View view){
        Intent intent = new Intent(this, CreateItemActivity.class);

        this.startActivity(intent);
    }
}
