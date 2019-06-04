package com.cookandroid.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE reservation (_idx INTEGER PRIMARY KEY AUTOINCREMENT, _time TEXT, _room TEXT, _people INTEGER, _limit INTEGER, _nowtime TEXT, _id TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE reservation");
        onCreate(db);
    }
    public String select_join(){ // 관리자
        SQLiteDatabase db = getWritableDatabase();
        String list ="";
        Cursor cursor = db.rawQuery("select * from reservation INNER JOIN member ON reservation._id=member._id",null);

        while(cursor.moveToNext()){
            list += "번호 : "
                    +cursor.getInt(0)
                    +", 시간 :     "
                    +cursor.getString(1)
                    +", 방번호 : "
                    +cursor.getString(2)
                    +", 사람 수 : "
                    +cursor.getInt(3)
                    +", 제한 시간 : "
                    +cursor.getInt(4)
                    +", 예약시 시간 : "
                    +cursor.getString(5)
                    +"\n 아이디 : "
                    +cursor.getString(6)
                    +"\n\n";

            db.execSQL("insert into list values ("+cursor.getString(0)+",'"+cursor.getString(1)+"','"+cursor.getString(2)+"',"+cursor.getInt(3)+","+cursor.getInt(4)+",'"+cursor.getString(5)+"','"+cursor.getString(6)+"');");
        }
        Log.d("asdasdasd",list);
        return list;
    }
//
//    SQLiteDatabase db;
//    String sql;
//

//
    public String reservation_list(){
        SQLiteDatabase db = getReadableDatabase();
        String list ="";
        Cursor cursor = db.rawQuery("select * from reservation INNER JOIN member ON reservation._id=member._id",null);

        while(cursor.moveToNext()) {
            list += "번호 : "
                    + cursor.getInt(0)
                    + ", 시간 : "
                    + cursor.getString(1)
                    + ", 강의실 번호 : "
                    + cursor.getString(2)
                    + ", 사람 수 : "
                    + cursor.getInt(3)
                    + ", 제한 시간 : "
                    + cursor.getInt(4)
                    + ", 예약시 시간 : "
                    + cursor.getString(5)
                    +"\n 아이디 : "
                    +cursor.getString(6)
                    + "\n\n";
        }
        return list;

    }
    public void insert(String query){ // 삽입
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

}
