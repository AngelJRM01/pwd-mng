package com.example.pwd_mng;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemViewActivity extends AppCompatActivity {

    TextView nombrePass, userPass, linkPass;
    EditText pass, notesPass;
    ImageView favouritePass;
    int itemId;
    private ItemDbHelper dbHelper;
    private SQLiteDatabase db;
    Item item;
    ImageButton home;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_item_view);

        dbHelper = new ItemDbHelper(getApplicationContext(), "pwd-mng.db");
        db = dbHelper.getWritableDatabase();

        nombrePass = findViewById(R.id.nombrePassItemView);
        userPass = findViewById(R.id.userPassItemView);
        pass = findViewById(R.id.passItemView);
        linkPass = findViewById(R.id.linkItemView);
        notesPass = findViewById(R.id.notesItemView);
        favouritePass = findViewById(R.id.favouriteItemView);
        home = findViewById(R.id.btnHome);


        itemId = getIntent().getIntExtra("id",0);

        String[] columns = {
                ItemContract.ItemEntry._ID,
                ItemContract.ItemEntry.COLUMN_NAME_Name,
                ItemContract.ItemEntry.COLUMN_NAME_Username,
                ItemContract.ItemEntry.COLUMN_NAME_Password,
                ItemContract.ItemEntry.COLUMN_NAME_Favorite,
                ItemContract.ItemEntry.COLUMN_NAME_Link,
                ItemContract.ItemEntry.COLUMN_NAME_Notes
        };
        String where = ItemContract.ItemEntry._ID + " = ?";
        String[] whereArgs = { itemId+"" };
        Cursor cursor = db.query(ItemContract.ItemEntry.TABLE_NAME, columns, where, whereArgs, null, null, null);

        try {
            while (cursor.moveToNext()) {
                item = new Item(
                        cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry._ID)) ,
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Name)),
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Username)),
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Password)),
                        Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Favorite))),
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Link)),
                        cursor.getString(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Notes)));

                item.setFavourite(cursor.getInt(cursor.getColumnIndex(ItemContract.ItemEntry.COLUMN_NAME_Favorite)) == 1);

                nombrePass.setText(item.getNombre());
                userPass.setText(item.getUser());
                pass.setText(item.getPass());
                linkPass.setText(item.getLink());
                notesPass.setText(item.getNotes());

                System.out.println(item.getFavourite());

                linkPass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse(item.getLink());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                if(!item.getFavourite()){
                    favouritePass.setImageResource(R.drawable.star_empty);
                } else {
                    favouritePass.setImageResource(R.drawable.star_full);
                }

            }
        } finally {
            cursor.close();
        }

    }

    public void deleteItem(View view){
        String where = ItemContract.ItemEntry._ID + " = ?";
        String[] whereArgs = { itemId+"" };

        Toast.makeText(getApplicationContext(), "Se ha eliminado la contraseña " + nombrePass.getText().toString(), Toast.LENGTH_LONG).show();

        db.delete(ItemContract.ItemEntry.TABLE_NAME, where, whereArgs);

        Intent intent = new Intent(this, MainActivity.class);

        this.startActivity(intent);
    }

    public void editItem(View view){
        Intent intent = new Intent(this, EditItemActivity.class);
        intent.putExtra("id", itemId);
        intent.putExtra("nombrePass", item.getNombre());
        intent.putExtra("usuarioPass", item.getUser());
        intent.putExtra("pass", item.getPass());
        intent.putExtra("linkPass", item.getLink());
        intent.putExtra("notasPass", item.getNotes());
        intent.putExtra("favoritoPass", item.getFavourite());

        this.startActivity(intent);
    }

    public void backHome(View view){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

}
