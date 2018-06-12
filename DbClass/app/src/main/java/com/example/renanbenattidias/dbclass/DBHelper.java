package com.example.renanbenattidias.dbclass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by renanbenattidias on 27/03/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";

    private static final String TABLE_NAME = "contacts";

    private static final String COLUM_ID = "id";
    private static final String COLUM_NAME = "name";
    private static final String COLUM_EMAIL = "email";
    private static final String COLUM_PASSWORD = "password";

    private static final int COLUM_ID_ID = 0;
    private static final int COLUM_NAME_ID = 1;
    private static final int COLUM_EMAIL_ID = 2;
    private static final int COLUM_PASSWORD_ID = 3;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COLUM_ID + " INTEGER PRIMARY KEY NOT NULL, " + COLUM_NAME + " TEXT NOT NULL, " + COLUM_EMAIL + " TEXT NOT NULL UNIQUE, " + COLUM_PASSWORD + " TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + "";
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean createContact(Contact contact) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUM_NAME, contact.name());
        values.put(COLUM_EMAIL, contact.email());
        values.put(COLUM_PASSWORD, contact.password());

        long result = database.insert(TABLE_NAME, null, values);

        database.close();

        return result > -1 ? true : false;
    }

    public Contact findContactBy(String email) {
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = database.query(TABLE_NAME, new String[] { COLUM_ID, COLUM_NAME, COLUM_EMAIL, COLUM_PASSWORD },COLUM_EMAIL + "=?", new String[] {email}, null, null, null, null );
        if(cursor != null && cursor.moveToFirst()) {
            Contact contact = new Contact(cursor.getString(COLUM_EMAIL_ID), cursor.getString(COLUM_PASSWORD_ID), cursor.getString(COLUM_NAME_ID), cursor.getInt(COLUM_ID_ID));
            database.close();
            return contact;
        }

        database.close();

        return null;
    }

}

