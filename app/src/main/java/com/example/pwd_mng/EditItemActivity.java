package com.example.pwd_mng;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {

    private EditText textName;
    private EditText textUsername;
    private EditText textPassword;
    private EditText textLink;
    private EditText textNotes;
    private Switch switchFavourite;
    private ItemDbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        textName = findViewById(R.id.textName);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        textLink = findViewById(R.id.textLink);
        textNotes = findViewById(R.id.textNotes);
        switchFavourite = findViewById(R.id.switchFavourite);



        dbHelper = new ItemDbHelper(getApplicationContext(), "aaaasdaaabbaaaaaaa.db");
        db = dbHelper.getWritableDatabase();



    }


    public void editItem(View view){
        Toast.makeText(this, "R.string.badInput", Toast.LENGTH_LONG).show();
    }
}