package com.example.pwd_mng;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateItemActivity extends AppCompatActivity {

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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_create_item);

        textName = findViewById(R.id.textName);
        textUsername = findViewById(R.id.textUsername);
        textPassword = findViewById(R.id.textPassword);
        textLink = findViewById(R.id.textLink);
        textNotes = findViewById(R.id.textNotes);
        switchFavourite = findViewById(R.id.switchFavourite);

        dbHelper = new ItemDbHelper(getApplicationContext(), "pwd-mng.db");
        db = dbHelper.getWritableDatabase();
    }

    public void createItem(View view){

        if(textName.getText().toString().equals("")){
            Toast.makeText(this, R.string.name_danger, Toast.LENGTH_LONG).show();
        }else if(!textPassword.getText().toString().equals("") && textPassword.getText().length() < 8){
            Toast.makeText(this, R.string.password_danger, Toast.LENGTH_LONG).show();
        }else {
            ContentValues values = new ContentValues();
            values.put(ItemContract.ItemEntry.COLUMN_NAME_Name, String.valueOf(textName.getText()));
            values.put(ItemContract.ItemEntry.COLUMN_NAME_Username, String.valueOf(textUsername.getText()));
            values.put(ItemContract.ItemEntry.COLUMN_NAME_Password, String.valueOf(textPassword.getText()));
            values.put(ItemContract.ItemEntry.COLUMN_NAME_Link, String.valueOf(textLink.getText()));
            values.put(ItemContract.ItemEntry.COLUMN_NAME_Favorite, (switchFavourite.isChecked()) ? 1 : 0);
            values.put(ItemContract.ItemEntry.COLUMN_NAME_Notes, String.valueOf(textNotes.getText()));

            db.insert(ItemContract.ItemEntry.TABLE_NAME, null, values);

            Toast.makeText(this, R.string.item_created, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);

            this.startActivity(intent);
        }

    }

}
