package com.example.unitec.sqlitte.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unitec.sqlitte.DTO.NhanVienDTO;
import com.example.unitec.sqlitte.SQLiteHelper.TaoDataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Unitec on 26/10/2017.
 * Lop nay dung de ket noi database
 */

public class NhanVienDAO {
    SQLiteDatabase database;
    TaoDataBase taoDataBase;
    String[] columNhanVien = {TaoDataBase.ID_NHANVIEN,TaoDataBase.TEN_NHANVIEN};
    public NhanVienDAO(Context context){
        taoDataBase = new TaoDataBase(context);
    }

    public void open(){
        database = taoDataBase.getWritableDatabase();
    }

    public void close(){
        taoDataBase.close();
    }
    public boolean themNhanVien(NhanVienDTO nv){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TaoDataBase.TEN_NHANVIEN,nv.getTenNhanvien().toString());
        long id = database.insert(TaoDataBase.TABLE_NHANVIEN,null,contentValues);
        if(id != 0){
            return true;
        }
        else{
            return false;
        }
    }
    public List<NhanVienDTO> LayDanhSachNhanVien(){
        List<NhanVienDTO> danhsachNhanVien = new ArrayList<NhanVienDTO>();


       // Cursor cursor = database.query(TaoDataBase.TABLE_NHANVIEN,columNhanVien,null,null,null,null,null);
        String query = "select * from "+TaoDataBase.TABLE_NHANVIEN;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        if(!(cursor.isAfterLast())){

            int IdNhanVien = cursor.getInt(0);
            String TenNhanVien = cursor.getString(1);

            NhanVienDTO nhanVienDTO = new NhanVienDTO();
            nhanVienDTO.setId(IdNhanVien);
            nhanVienDTO.setTenNhanvien(TenNhanVien);

            danhsachNhanVien.add(nhanVienDTO);
            cursor.moveToNext();
        }
        return danhsachNhanVien;
    }

    public boolean XoaNhanVien(NhanVienDTO nhanVienDTO){
        int kt = database.delete(TaoDataBase.TABLE_NHANVIEN,TaoDataBase.ID_NHANVIEN+"="+nhanVienDTO.getId(),null);
        if (kt != 0){
            return true;
        }else {
            return false;
        }
    }

}
