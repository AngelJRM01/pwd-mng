package com.example.pwd_mng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

        dbHelper = new ItemDbHelper(getApplicationContext(), "pwd-mng.db");
        db = dbHelper.getWritableDatabase();



    }


    public void editItem(View view){
        Toast.makeText(this, "R.string.badInput", Toast.LENGTH_LONG).show();
    }
}