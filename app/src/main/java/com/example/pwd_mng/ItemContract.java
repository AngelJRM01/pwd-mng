package com.example.pwd_mng;

import android.provider.BaseColumns;

public final class ItemContract {

    private ItemContract() {}

    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "Item";
        public static final String COLUMN_NAME_Name = "name";
        public static final String COLUMN_NAME_Username = "username";
        public static final String COLUMN_NAME_Password = "password";
        public static final String COLUMN_NAME_Link = "link";
        public static final String COLUMN_NAME_Favorite = "favorite";
        public static final String COLUMN_NAME_Notes = "notes";
    }
}
