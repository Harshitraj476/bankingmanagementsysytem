package com.example.banking_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseUserNames extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseUserNames(@Nullable Context context) {
        super(context, "User.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Ram',4567.00,'Ram@gmail.com','XXXXXXXXXXXX0243','ABC01234')");
        db.execSQL("insert into user_table values(2345678901,'Shyam',987654.00,'Shyam@gmail.com','XXXXXXXXXXXX8988','ABC05678')");
        db.execSQL("insert into user_table values(0987654321,'Seeta',21520.05,'Seeta@gmail.com','XXXXXXXXXXXX1215','ABC01479')");
        db.execSQL("insert into user_table values(1098765432,'Geeta',6626.09,'Geeta@gmail.com','XXXXXXXXXXXX1458','ABC01234')");
        db.execSQL("insert into user_table values(5678904321,'Ramesh',12350.02,'Ramesh@gmail.com','XXXXXXXXXXXX9818','ABC00937')");
        db.execSQL("insert into user_table values(3456789012,'Suresh',98664.15,'Suresh@gmail.com','XXXXXXXXXXXX8653','ABC01667')");
        db.execSQL("insert into user_table values(7654321098,'Arjun',200.80,'Arjun@gmail.com','XXXXXXXXXXXX02231','ABC01789')");
        db.execSQL("insert into user_table values(9988776655,'Krishna',6112233.88,'Krishna@gmail.com','XXXXXXXXXXXX7896','ABC02324')");
        db.execSQL("insert into user_table values(1122334455,'Rahul',123445.67,'Rahul@gmail.com','XXXXXXXXXXXX9944','ABC01339')");
        db.execSQL("insert into user_table values(0011223344,'Laxman',9653.80,'Laxman@gmail.com','XXXXXXXXXXXX2319','ABC04340')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user_table", null);
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from transfers_table", null);
    }

    public boolean insertTransferData(String date, String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}