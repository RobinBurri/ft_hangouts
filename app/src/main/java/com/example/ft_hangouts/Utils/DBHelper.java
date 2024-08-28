package com.example.ft_hangouts.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ft_hangouts.Models.Contact;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance;

    private static final String CONTACT_TABLE = "CONTACT_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    private static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_LAST_NAME = "LAST_NAME";
    private static final String COLUMN_NICK_NAME = "NICK_NAME";
    private static final String COLUMN_PICTURE = "PICTURE";
    private static final String MESSAGE_TABLE = "MESSAGE_TABLE";
    private static final String COLUMN_MESSAGE = "MESSAGE";
    private static final String COLUMN_DATE = "DATE";
    private static final String COLUMN_SENDER_ID = "SENDER_ID";
    private static final String COLUMN_RECEIVER_ID = "RECEIVER_ID";

    public DBHelper(Context context) {
        super(context, "contact.db", null, 1);
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContactTableStatement = "CREATE TABLE " + CONTACT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PHONE_NUMBER + " TEXT, " + COLUMN_FIRST_NAME + " TEXT, " + COLUMN_LAST_NAME + " TEXT, " + COLUMN_NICK_NAME + " TEXT, " + COLUMN_PICTURE + " TEXT)";
        db.execSQL(createContactTableStatement);
        String createMessageTableStatement = "CREATE TABLE " + MESSAGE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MESSAGE + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_SENDER_ID + " INT, " + COLUMN_RECEIVER_ID + " INT)";
        db.execSQL(createMessageTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropContactTableStatement = "DROP TABLE IF EXISTS " + CONTACT_TABLE;
        db.execSQL(dropContactTableStatement);
        String dropMessageTableStatement = "DROP TABLE IF EXISTS " + MESSAGE_TABLE;
        db.execSQL(dropMessageTableStatement);
    }

    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + CONTACT_TABLE + " WHERE " + COLUMN_ID + " = " + id;
        try (Cursor cursor = db.rawQuery(queryString, null)) {
            if (cursor.moveToFirst()) {
                int contactId = cursor.getInt(0);
                String phoneNumber = cursor.getString(1);
                String firstName = cursor.getString(2);
                String lastName = cursor.getString(3);
                String nickName = cursor.getString(4);
                String picture = cursor.getString(5);
                return new Contact(contactId, phoneNumber, firstName, picture, lastName, nickName);
            }
        }
        return null;
    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertContactStatement = "INSERT INTO " + CONTACT_TABLE + " (" + COLUMN_PHONE_NUMBER + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ", " + COLUMN_NICK_NAME + ", " + COLUMN_PICTURE + ") VALUES ('" + contact.getPhoneNumber() + "', '" + contact.getFirstName() + "', '" + contact.getLastName() + "', '" + contact.getNickName() + "', '" + contact.getPicture() + "')";
        db.execSQL(insertContactStatement);
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteContactStatement = "DELETE FROM " + CONTACT_TABLE + " WHERE " + COLUMN_ID + " = " + id;
        db.execSQL(deleteContactStatement);
    }

    public void updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        String updateContactStatement = "UPDATE " + CONTACT_TABLE + " SET " + COLUMN_PHONE_NUMBER + " = '" + contact.getPhoneNumber() + "', " + COLUMN_FIRST_NAME + " = '" + contact.getFirstName() + "', " + COLUMN_LAST_NAME + " = '" + contact.getLastName() + "', " + COLUMN_NICK_NAME + " = '" + contact.getNickName() + "', " + COLUMN_PICTURE + " = '" + contact.getPicture() + "' WHERE " + COLUMN_ID + " = " + contact.getId();
        db.execSQL(updateContactStatement);
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + CONTACT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(queryString, null)) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String phoneNumber = cursor.getString(1);
                    String firstName = cursor.getString(2);
                    String lastName = cursor.getString(3);
                    String nickName = cursor.getString(4);
                    String picture = cursor.getString(5);
                    Contact newContact = new Contact(id, phoneNumber, firstName, picture, lastName, nickName);
                    returnList.add(newContact);
                } while (cursor.moveToNext());
            }
        }
        return returnList;
    }
}
