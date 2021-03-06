package com.example.sravyanaguboyina.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sravya naguboyina on 06-10-2017.
 */

public class thursday_database extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "thursday.db";
    public static final String TABLE_NAME = "thursday_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SUBJECT";
    public static final String COL_3 = "PROFESSOR_NAME";
    public static final String COL_4 = "START_TIME";
    public static final String COL_5 = "END_TIME";
    public static final String COL_6 = "ROOM_NO";
    public thursday_database(Context context) {
        super(context,DATABASE_NAME, null, 1);
        //SQLiteDatabase db=this.getWritableDatabase(); //see the database created
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,SUBJECT TEXT,PROFESSOR_NAME TEXT,START_TIME TEXT,END_TIME TEXT,ROOM_NO TEXT)");
        Log.d("HI","dd");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String subject,String prof,String start,String end,String room){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,subject);
        contentValues.put(COL_3,prof);
        contentValues.put(COL_4,start);
        contentValues.put(COL_5,end);
        contentValues.put(COL_6,room);
        //to insert data
        long result = db.insert(TABLE_NAME,null,contentValues); //if data is creted it returns -1
        if(result== -1){
            return false;
        }
        else
            return true;

    }
    //cursor provieds random read and write
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public String showData(int id) {
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{COL_1, COL_2, COL_3, COL_4, COL_5, COL_6};
        Cursor cursor = db.query(TABLE_NAME, columns, COL_1 + "=" + id, null, null, null, null);
        String subj, prof, st, en, room;
        mon_addclass m = new mon_addclass();
        if (cursor.moveToFirst()) {
            do {
                buffer.append(cursor.getString(cursor.getColumnIndex(this.COL_2)));
                buffer.append(" ");
                buffer.append(cursor.getString(cursor.getColumnIndex(this.COL_3)));
                buffer.append(" ");
                buffer.append(cursor.getString(cursor.getColumnIndex(this.COL_4)));
                buffer.append(" ");
                buffer.append(cursor.getString(cursor.getColumnIndex(this.COL_5)));
                buffer.append(" ");
                buffer.append(cursor.getString(cursor.getColumnIndex(this.COL_6)));
                buffer.append(" ");

            } while (cursor.moveToNext());

        }
        return buffer.toString();
    }

    public boolean updateData(String id,String subject,String prof,String start,String end,String room){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,subject);
        contentValues.put(COL_3,prof);
        contentValues.put(COL_4,start);
        contentValues.put(COL_5,end);
        contentValues.put(COL_6,room);
        db.update(TABLE_NAME,contentValues, "ID = ?",new String[] {id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
