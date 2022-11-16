package com.example.pwd_mng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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
    private ListItem listItem = new ListItem();

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

        listItem.setNombre(getIntent().getStringExtra("nombrePass"));
        listItem.setUser(getIntent().getStringExtra("usuarioPass"));
        listItem.setPass(getIntent().getStringExtra("pass"));
        listItem.setLink(getIntent().getStringExtra("linkPass"));
        listItem.setNotes(getIntent().getStringExtra("notasPass"));
        listItem.setFavourite(getIntent().getBooleanExtra("favoritoPass", false));
        listItem.setId(getIntent().getIntExtra("id", 0));

        textName.setText(listItem.getNombre());
        textUsername.setText(listItem.getUser());
        textPassword.setText(listItem.getPass());
        textLink.setText(listItem.getLink());
        textNotes.setText(listItem.getNotes());
        switchFavourite.setChecked(listItem.getFavourite());

        dbHelper = new ItemDbHelper(getApplicationContext(), "aaaasdaaabbaaaaaaa.db");
        db = dbHelper.getWritableDatabase();



    }


    public void editItem(View view){

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

            String where = ItemContract.ItemEntry._ID + " = ?";
            String[] whereArgs = { listItem.getId()+"" };
            db.update(ItemContract.ItemEntry.TABLE_NAME, values, where, whereArgs);

            Toast.makeText(this, R.string.item_edited, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, MainActivity.class);

            this.startActivity(intent);
        }

    }
}