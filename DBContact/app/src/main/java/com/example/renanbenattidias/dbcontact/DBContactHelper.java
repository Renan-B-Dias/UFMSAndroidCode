package com.example.renanbenattidias.dbcontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by renanbenattidias on 04/04/18.
 */

public class DBContactHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";

    private static final String TABLE_NAME = "contacts";

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";

    private static final int ID_ID = 0;
    private static final int NAME_ID = 1;
    private static final int AGE_ID = 2;
    private static final int ADDRESS_ID = 3;
    private static final int PHONE_ID = 4;

    public DBContactHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY NOT NULL, " + NAME + " TEXT NOT NULL, " + AGE + " INTEGER NOT NULL, " + ADDRESS + " TEXT NOT NULL, " + PHONE + " TEXT NOT NULL)";
        database.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + "";
        database.execSQL(sql);
        onCreate(database);
    }

    public boolean create(Contact contact) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, contact.name());
        values.put(AGE, contact.age());
        values.put(ADDRESS, contact.address());
        values.put(PHONE, contact.phone());

        long result = database.insert(TABLE_NAME, null, values);
        database.close();

        return result > -1;
    }

    public boolean update(Contact contact) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, contact.name());
        values.put(AGE, contact.age());
        values.put(ADDRESS, contact.address());
        values.put(PHONE, contact.phone());

        long result = database.update(TABLE_NAME, values, "id="+contact.id(), null);
        database.close();

        return result > -1;
    }

    public List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery(sql, null);   // execSQL??

        if(cursor.moveToFirst()) {
            do {
                contacts.add(
                        new Contact(cursor.getInt(ID_ID), cursor.getString(NAME_ID),
                                cursor.getInt(AGE_ID), cursor.getString(ADDRESS_ID),
                                cursor.getString(PHONE_ID))
                );
            } while(cursor.moveToNext());
        }
        return contacts;
    }
}

//    public List<Book> getAllBooks() {
//        List<Book> list = new ArrayList<>();
//
//        String sql = "select * from book";
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = db.rawQuery(sql, null);
//
//        if(cursor.moveToFirst()) {
//            do {
//                list.add(new Book(cursor.getLong(BOOK_ID), cursor.getString(BOOK_NAME), cursor.getInt(BOOK_PAGES), DbBitmapUtility.getImage(cursor.getBlob(BOOK_IMAGE_BITMAP))));
//            } while(cursor.moveToNext());
//        }
//
//        return list;
//    }
//    public Contact findContactBy(String email) {
//        SQLiteDatabase database = this.getReadableDatabase();
//
//        Cursor cursor = database.query(TABLE_NAME, new String[] { COLUM_ID, COLUM_NAME, COLUM_EMAIL, COLUM_PASSWORD },COLUM_EMAIL + "=?", new String[] {email}, null, null, null, null );
//        if(cursor != null && cursor.moveToFirst()) {
//            Contact contact = new Contact(cursor.getString(COLUM_EMAIL_ID), cursor.getString(COLUM_PASSWORD_ID), cursor.getString(COLUM_NAME_ID), cursor.getInt(COLUM_ID_ID));
//            database.close();
//            return contact;
//        }
//
//        database.close();
//
//        return null;
//    }