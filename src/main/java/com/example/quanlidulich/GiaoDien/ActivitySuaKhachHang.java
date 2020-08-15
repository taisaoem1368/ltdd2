package com.example.quanlidulich.GiaoDien;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.Model.KhachHangModel;
import com.example.quanlidulich.R;

public class ActivitySuaKhachHang extends AppCompatActivity {

    EditText edtMaKH, edtTenKH, edtDiaChi, edtSoDT;
    Button btnLuu, btnXoa;

    KhachHangModel khachHang = new KhachHangModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suakh);
        setControl();
        setEvent();
        layDuLieuKH();

        //Lấy tên cho trang details
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            String titleFormEditKH = ActivitySuaKhachHang.this.getResources().getString(R.string.titleSuaKH);
            ab.setTitle(titleFormEditKH + " " +  khachHang.getMaKH());
        }
    }

    private void setEvent() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaKhachHangVaoMang();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmXoa();
            }
        });
    }

    private void suaKhachHangVaoMang() {
        DBDuLich myDB =  new DBDuLich(this);
        final String maKH = edtMaKH.getText().toString();
        final String tenKH = edtTenKH.getText().toString();
        final String diaChi = edtDiaChi.getText().toString();

        if(TextUtils.isEmpty(maKH) || TextUtils.isEmpty(tenKH) || TextUtils.isEmpty(diaChi)){
            String toastNull = ActivitySuaKhachHang.this.getResources().getString(R.string.alertNull);
            Toast.makeText(ActivitySuaKhachHang.this, toastNull, Toast.LENGTH_LONG).show();
        }
        else{
            String toastSuccess = ActivitySuaKhachHang.this.getResources().getString(R.string.alertSuccess);
            myDB.SuaKH(new KhachHangModel(khachHang.getSttKH(),maKH,tenKH,diaChi));
            Toast.makeText(ActivitySuaKhachHang.this, toastSuccess, Toast.LENGTH_LONG).show();
        }
    }

    //Lấy dữ liệu đổ vào trang sửa khi click vào item
    void layDuLieuKH(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("maKH") &&
                getIntent().hasExtra("tenKH") &&
                getIntent().hasExtra("diaChi")){
            //Getting Data from Intent
            int id = -1;
            khachHang.setSttKH(getIntent().getIntExtra("id",id));
            khachHang.setMaKH(getIntent().getStringExtra("maKH"));
            khachHang.setTenKH(getIntent().getStringExtra("tenKH"));
            khachHang.setDiaChi(getIntent().getStringExtra("diaChi"));

            //Setting Intent Data
            edtMaKH.setText(khachHang.getMaKH());
            edtTenKH.setText(khachHang.getTenKH());
            edtDiaChi.setText(khachHang.getDiaChi());
        } else {
            String toastMessage = ActivitySuaKhachHang.this.getResources().getString(R.string.noData);
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    void confirmXoa() {
        String toastTittle = ActivitySuaKhachHang.this.getResources().getString(R.string.confirmDeleteTitle);
        String toastMessage = ActivitySuaKhachHang.this.getResources().getString(R.string.confirmDeleteMessage);
        String toastYes = ActivitySuaKhachHang.this.getResources().getString(R.string.confirmYes);
        String toastNo = ActivitySuaKhachHang.this.getResources().getString(R.string.confirmNo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(toastTittle + khachHang.getTenKH());
        builder.setMessage(toastMessage + khachHang.getTenKH());
        builder.setPositiveButton(toastYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBDuLich myDB = new DBDuLich(ActivitySuaKhachHang.this);
                myDB.XoaKH(khachHang);
                finish();
            }
        });
        builder.setNegativeButton(toastNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create().show();
    }

    private void setControl() {
        edtMaKH = findViewById(R.id.suaMaKH);
        edtTenKH = findViewById(R.id.suaTen);
        edtDiaChi = findViewById(R.id.suaDiaChi);
        btnLuu = findViewById(R.id.save_button);
        btnXoa = findViewById(R.id.delete_button);
    }
}
