package com.mvi.broadcastdemo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, DbContact.DATABASE_NAME, null, DbContact.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DbContact.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL(DbContact.DROPTABLE);
        onCreate(sqLiteDatabase);
    }

    public void saveIncomingNumber(String incomingNo, SQLiteDatabase sqLiteDatabase) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContact.INCOMING_NUMBER, incomingNo);
        sqLiteDatabase.insert(DbContact.TABLE_NAME, null, contentValues);
    }

    @SuppressLint("Recycle")
    public Cursor readIncomingNos(SQLiteDatabase sqLiteDatabase) {
        String[] projection = {DbContact.ID, DbContact.INCOMING_NUMBER};
        return (sqLiteDatabase.query(DbContact.TABLE_NAME, projection, null, null, null, null, null));
    }


}
