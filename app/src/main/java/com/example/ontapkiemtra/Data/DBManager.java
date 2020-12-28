package com.example.ontapkiemtra.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ontapkiemtra.PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "phonebook";

    String TB_PHONEBOOK = "book";
    String ID_PHONE = "id";
    String NAME_PHONE = "name";
    String NUMBER_PHONE = "number";

    String sql = "CREATE TABLE " + TB_PHONEBOOK + " (" +
            ID_PHONE + " integer primary key," +
            NAME_PHONE + " TEXT, " +
            NUMBER_PHONE + " TEXT )";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //add new
    public void addNewPhone(PhoneBook phoneBook) {

        //mo ket noi
        SQLiteDatabase db = this.getWritableDatabase();

        //luu gia tri
        ContentValues values = new ContentValues();
        values.put(NAME_PHONE, phoneBook.getName());
        values.put(NUMBER_PHONE, phoneBook.getNumber());
        db.insert(TB_PHONEBOOK, null, values);

        //dong ket noi
        db.close();
    }


    //get list phone book
    public List<PhoneBook> selectListTable() {
        String query_selectall = "Select * from " + TB_PHONEBOOK;
        List<PhoneBook> listTable = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                PhoneBook table = new PhoneBook();

                table.setId(cursor.getInt(0));
                table.setName(cursor.getString(1));
                table.setNumber(cursor.getString(2));
                listTable.add(table);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTable;
    }


    //delete all
    public void deleteNumber() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TB_PHONEBOOK, null, null);
    }

    /*delete with id*/
    public void deletenum(int idnumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_getNum = "delete from " + TB_PHONEBOOK + " where id =" + idnumber;
        db.execSQL(sql_getNum);
    }

    public void updateNumber(int idnumber, String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql_getNum = "update " + TB_PHONEBOOK +
                "   set   name ='" + name + "',number='"+number+"'" +
                " where id =" + idnumber;
        db.execSQL(sql_getNum);
    }


    public List<PhoneBook> searchName(String name) {
        String query_selectall = "Select * from " + TB_PHONEBOOK+" where name='"+name+"'";
        List<PhoneBook> listTable = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                PhoneBook table = new PhoneBook();

                table.setId(cursor.getInt(0));
                table.setName(cursor.getString(1));
                table.setNumber(cursor.getString(2));
                listTable.add(table);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTable;
    }

    public List<PhoneBook> searchNumber(String name) {
        String query_selectall = "Select * from " + TB_PHONEBOOK+"  where number='"+name+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);
        List<PhoneBook> listTable = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                PhoneBook table = new PhoneBook();

                table.setId(cursor.getInt(0));
                table.setName(cursor.getString(1));
                table.setNumber(cursor.getString(2));
                listTable.add(table);
            } while (cursor.moveToNext());
        }
        return listTable;
    }

}
