package com.example.quanlidulich.GiaoDien;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.Model.KhachHangModel;
import com.example.quanlidulich.R;

public class ActivityThemKhachHang extends AppCompatActivity {
    EditText edtMaKH, edtTenKH, edtDiaChi;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themkh);
        setControl();
        setEvent();

        //Đổi tên actionbar theo ngôn ngữ đã đổi:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.titleThemKH));
    }

    private void setEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKhachHangVaoMang();
            }
        });
    }

    private void addKhachHangVaoMang() {
        DBDuLich myDB = new DBDuLich(this);
        final String maKH = edtMaKH.getText().toString();
        final String tenKH = edtTenKH.getText().toString();
        final String diaChi = edtDiaChi.getText().toString();

        if(TextUtils.isEmpty(maKH) || TextUtils.isEmpty(tenKH) || TextUtils.isEmpty(diaChi)){
            String toastNull = ActivityThemKhachHang.this.getResources().getString(R.string.alertNull);
            Toast.makeText(ActivityThemKhachHang.this, toastNull, Toast.LENGTH_LONG).show();
        }
        else{
            String toastSuccess = ActivityThemKhachHang.this.getResources().getString(R.string.alertSuccess);
            KhachHangModel khachHang = new KhachHangModel(maKH,tenKH,diaChi);
            Toast.makeText(ActivityThemKhachHang.this, toastSuccess, Toast.LENGTH_LONG).show();
            myDB.ThemKH(khachHang);
        }
        edtMaKH.setText("");
        edtTenKH.setText("");
        edtDiaChi.setText("");
    }
    private void setControl() {
        edtMaKH = findViewById(R.id.nhapMaKH);
        edtTenKH = findViewById(R.id.nhapTen);
        edtDiaChi = findViewById(R.id.nhapDiaChi);
        btnThem = findViewById(R.id.add_button);
    }
}
