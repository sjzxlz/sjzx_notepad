package com.example.sjzx.sjzx_notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDB extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "notes";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    public static final String IMG ="img";
    public static final String VIDEO = "video";
    public static final String ID = "_id";
    public static final String TIME = "time";

    public NotesDB(Context context) {
        super(context, "notes", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + CONTENT
                + " TEXT NOT NULL,"  + TITLE + " TEXT NOT NULL,"
                + IMG + " TEXT NOT NULL，"
                + VIDEO + " TEXT NOT NULL,"
                + TIME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
