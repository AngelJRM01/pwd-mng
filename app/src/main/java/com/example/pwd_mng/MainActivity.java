package com.example.pwd_mng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListItem> listDatos;
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
                ListItem listItem = new ListItem(
                        cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry._ID)) ,
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Name)),
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Username)));

                listItem.setFavourite(cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Favorite)) == 1);

                listDatos.add(listItem);
            }
        } finally {
            cursor.close();
        }
    }


    /*private void initItem() {
        // Adición de valores a la BD
        ContentValues values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Name, "Amazo1");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Username, "prueba@gmail.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Password, "123");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Link, "amazon.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Favorite, 0);
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Notes, "Importantesdaaaaaaaaaaaaaaaaaaaaaaaaaaadsaaaaaaaaaaaaaaaa");
        db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Name, "Instagram");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Username, "prueba@gmail.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Password, "123456");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Link, "instagram.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Favorite, 1);
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Notes, "Importante Importante Importante Importante Importante Importante Importante Importante Importante Importante");
        db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Name, "Github");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Username, "prueba@gmail.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Password, "12eweweqw3");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Link, "https://github.com/");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Favorite, 0);
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Notes, "Importafdfdfnte");
        db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Name, "Discord");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Username, "prueba@gmail.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Password, "1212212122");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Link, "discord.com");
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Favorite, 1);
        values.put(ItemContract.ItemEntry.COLUMN_NAME_Notes, "Importanteewewew");
        db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);
    }*/

    public void changeActivityToCreate(View view){
        Intent intent = new Intent(this, CreateItemActivity.class);

        this.startActivity(intent);
    }
}