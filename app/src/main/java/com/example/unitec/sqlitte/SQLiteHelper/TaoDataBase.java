package com.example.unitec.sqlitte.SQLiteHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Unitec on 26/10/2017.
 * Class nay chi de tao bang, update bang
 */

public class TaoDataBase extends SQLiteOpenHelper {
    public static final String DB_QUANLYNHANVIEN = "Quan ly nhan vien";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NHANVIEN = "NHANVIEN";
    public static final String ID_NHANVIEN = "_id";
    public static final String TEN_NHANVIEN = "tenNhanVien";


    public TaoDataBase(Context context) {
        super(context, DB_QUANLYNHANVIEN, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+ TABLE_NHANVIEN + " ( "+ID_NHANVIEN +
                " INTEGER PRIMARY KEY AUTOINCREMENT,"+TEN_NHANVIEN+" TEXT);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXITS "+TABLE_NHANVIEN);
        onCreate(sqLiteDatabase);

    }


}
