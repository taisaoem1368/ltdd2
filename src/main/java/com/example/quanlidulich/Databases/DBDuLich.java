package com.example.quanlidulich.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.quanlidulich.Model.KhachHangModel;
import com.example.quanlidulich.Model.PhieuDangKyModel;
import com.example.quanlidulich.Model.TourModel;

import java.util.ArrayList;

public class DBDuLich {
    DBHelper dbHelper;
    Context context;
    public DBDuLich(Context context) {
        dbHelper= new DBHelper(context);
        this.context = context;
    }
//------------------------------------------------Khách Hàng------------------------------------------------
    public ArrayList<KhachHangModel> listKhachHangs(){
        String sql = "select * from " + "KHACHHANG";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<KhachHangModel> khachHangs = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Integer _id = (cursor.getInt(0));
                String maKH = cursor.getString(1);
                String tenKH = cursor.getString(2);
                String diaChi = cursor.getString(3);
                khachHangs.add(new KhachHangModel(_id,maKH,tenKH,diaChi));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return khachHangs;
    }

    public void ThemKH(KhachHangModel khachHang){
        ContentValues values = new ContentValues();
        values.put("makh", khachHang.getMaKH());
        values.put("tenkh", khachHang.getTenKH());
        values.put("diachi", khachHang.getDiaChi());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("KHACHHANG", null, values);
    }

    public void SuaKH(KhachHangModel khachHang){
        ContentValues values = new ContentValues();
        values.put("makh", khachHang.getMaKH());
        values.put("tenkh", khachHang.getTenKH());
        values.put("diachi", khachHang.getDiaChi());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("KHACHHANG", values, "_id"	+ "	= ?", new String[]{String.valueOf(khachHang.getSttKH())});
    }

    public void XoaKH(KhachHangModel khachHang){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete("KHACHHANG", "_id=?", new String[]{String.valueOf(khachHang.getSttKH())});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Đã xóa.", Toast.LENGTH_SHORT).show();
        }
    }

    //------------------------------------------------Danh sách tour------------------------------------------------
    public ArrayList<TourModel> listTourModel(){
        String sql = "select * from " + "DSTOUR";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<TourModel> tourModels = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Integer _id = (cursor.getInt(0));
                String maTour = cursor.getString(1);
                String loTrinh = cursor.getString(2);
                String hanhTrinh = cursor.getString(3);
                Integer giaTour = cursor.getInt(4);
                tourModels.add(new TourModel(_id,maTour,loTrinh,hanhTrinh,giaTour));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return tourModels;
    }

    public void ThemTour(TourModel tourModel){
        ContentValues values = new ContentValues();
        values.put("matour", tourModel.getMaTour());
        values.put("lotrinh", tourModel.getLoTrinh());
        values.put("hanhtrinh", tourModel.getHanhTrinh());
        values.put("giatour", tourModel.getGiaTour());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("DSTOUR", null, values);
    }

    public void SuaTour(TourModel tourModel){
        ContentValues values = new ContentValues();
        values.put("matour", tourModel.getMaTour());
        values.put("lotrinh", tourModel.getLoTrinh());
        values.put("hanhtrinh", tourModel.getHanhTrinh());
        values.put("giatour", tourModel.getGiaTour());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("DSTOUR", values, "_id"	+ "	= ?", new String[]{String.valueOf(tourModel.getSttTour())});
    }

    public void XoaTour(TourModel tourModel){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete("DSTOUR", "_id=?", new String[]{String.valueOf(tourModel.getSttTour())});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Đã xóa.", Toast.LENGTH_SHORT).show();
        }
    }

    //------------------------------------------------Danh sách phiếu đăng ký------------------------------------------------
    public ArrayList<PhieuDangKyModel> listPhieuDangKy(){
        String sql = "select * from " + "PHIEUDANGKY";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<PhieuDangKyModel> listPhieuDangKy = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                Integer _id = (cursor.getInt(0));
                String soPhieu = cursor.getString(1);
                String maKH = cursor.getString(2);
                String maTour = cursor.getString(3);
                String ngayDK = cursor.getString(4);
                Integer soNguoi = cursor.getInt(5);
                listPhieuDangKy.add(new PhieuDangKyModel(_id,soPhieu,maKH,maTour,ngayDK,soNguoi));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listPhieuDangKy;
    }

    public void ThemPhieu (PhieuDangKyModel phieuDangKyModel){
        ContentValues values = new ContentValues();
        values.put("sophieu", phieuDangKyModel.getSoPhieu());
        values.put("makh", phieuDangKyModel.getMaKH());
        values.put("matour", phieuDangKyModel.getMaTour());
        values.put("ngaydk", phieuDangKyModel.getNgayDK());
        values.put("songuoi", phieuDangKyModel.getSoNguoi());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.insert("PHIEUDANGKY", null, values);
    }

    public void SuaPhieu(PhieuDangKyModel phieuDangKyModel){
        ContentValues values = new ContentValues();
        values.put("sophieu", phieuDangKyModel.getSoPhieu());
        values.put("makh", phieuDangKyModel.getMaKH());
        values.put("matour", phieuDangKyModel.getMaTour());
        values.put("ngaydk", phieuDangKyModel.getNgayDK());
        values.put("songuoi", phieuDangKyModel.getSoNguoi());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("PHIEUDANGKY", values, "_id=?", new String[]{String.valueOf(phieuDangKyModel.getSttSoPhieuDK())});
    }

    public void XoaPhieu(PhieuDangKyModel phieuDangKyModel){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.delete("PHIEUDANGKY", "_id=?", new String[]{String.valueOf(phieuDangKyModel.getSttSoPhieuDK())});
        if(result == -1){
            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Đã xóa.", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<String> getAllMaKH(){
        ArrayList<String> listMaKH = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM " + "KHACHHANG";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String maKH = cursor.getString(cursor.getColumnIndex("makh"));
                    listMaKH.add(maKH);
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e)  {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return listMaKH;
    }

    public ArrayList<String> getAllMaTour(){
        ArrayList<String> listMaTour = new ArrayList<String>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM " + "DSTOUR";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String maTour = cursor.getString(cursor.getColumnIndex("matour"));
                    listMaTour.add(maTour);
                }
            }
            db.setTransactionSuccessful();
        } catch (Exception e)  {
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return listMaTour;
    }
}
