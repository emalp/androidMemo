package com.example.emalp.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Emalp on 30/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lasttest.db";
    public static final String USERS_TABLE_NAME = "contacts";
    public static final String PASSWORD_COLUMN_ID = "password";
    public static final String MEMO_COLUMN_ID = "memo";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            // db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //myDB = this.openOrCreateDatabase("DatabaseName", MODE_PRIVATE, null);

        db.execSQL(
                "create table contacts " +
                        "(id integer primary key autoincrement, password text not null, memo text not null)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);


    }

    public String checkDatabase(){
        SQLiteDatabase testdb = this.getWritableDatabase();
        String nameofdb = testdb.getPath();

       // checking 2
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", "12345");
        contentValues.put("memo", "This is a test memo");
        long again = testdb.insert("contacts", null, contentValues);
        if(again<0){
            return "Failed!! :: " + nameofdb;
        }
        else{
            return "Success!! :: " + nameofdb;
        }
    }

    public long register( String password, String memo){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("memo", memo);
        long test = db.insert("contacts", null, contentValues);


        return test;
    }

    public Cursor login(String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where  password =" + password + "", null );
        return res;
    }

    public Integer deleteMemo (String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "password = ? ",
                new String[] { password });
    }
}
