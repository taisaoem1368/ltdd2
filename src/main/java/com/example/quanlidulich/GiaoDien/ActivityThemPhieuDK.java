package com.example.quanlidulich.GiaoDien;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.Model.PhieuDangKyModel;
import com.example.quanlidulich.R;

import java.util.ArrayList;

public class ActivityThemPhieuDK extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText edtSoPhieu, edtNgayDK, edtSoNguoi;
    Spinner spnMaKH, spnMaTour;
    Button btnThem;
    public DBDuLich myDB;
    private String maKH = "";
    private String maTour = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themphieudk);
        setControl();
        setEvent();

        //Đổi tên actionbar theo ngôn ngữ đã đổi:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm phiếu đăng ký");
    }

    private void setEvent() {
        spnMaKH.setOnItemSelectedListener(this);
        spnMaTour.setOnItemSelectedListener(this);
        loadSpinnerData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPhieuDKVaoMang();
            }
        });
    }

    private void loadSpinnerData() {
        myDB = new DBDuLich(getApplicationContext());

        //Set giá trị cho spinner Mã KH
        ArrayList<String> listMaKH = myDB.getAllMaKH();
        ArrayAdapter<String> adapterMaKH = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listMaKH);
        adapterMaKH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaKH.setAdapter(adapterMaKH);

        //Set giá trị cho spinner Mã Tour
        ArrayList<String> listMaTour = myDB.getAllMaTour();
        ArrayAdapter<String> adapterMaTour = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listMaTour);
        adapterMaKH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaTour.setAdapter(adapterMaTour);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.nhapSpinnerMaKH) {
            maKH = parent.getSelectedItem().toString();
            //Toast.makeText(parent.getContext(), "You selected: " + maKH, Toast.LENGTH_LONG).show();
        } else if (spinner.getId() == R.id.nhapSpinnerMaTour) {
            maTour = parent.getSelectedItem().toString();
            //Toast.makeText(parent.getContext(), "You selected: " + maTour, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    private void addPhieuDKVaoMang() {
        DBDuLich myDB = new DBDuLich(this);
        try {
            final String soPhieu = edtSoPhieu.getText().toString();
            final String ngayDangKy = edtNgayDK.getText().toString();
            final Integer soNguoi = Integer.parseInt(edtSoNguoi.getText().toString());

            if(TextUtils.isEmpty(soPhieu) || TextUtils.isEmpty(ngayDangKy) || TextUtils.isEmpty(maKH) || TextUtils.isEmpty(maTour)){
                String toastNull = ActivityThemPhieuDK.this.getResources().getString(R.string.alertNull);
                Toast.makeText(ActivityThemPhieuDK.this, toastNull, Toast.LENGTH_LONG).show();
            }
            else{
                String toastSuccess = ActivityThemPhieuDK.this.getResources().getString(R.string.alertSuccess);
                PhieuDangKyModel phieuDangKyModel = new PhieuDangKyModel(soPhieu, maKH, maTour,ngayDangKy,soNguoi);
                Toast.makeText(ActivityThemPhieuDK.this, toastSuccess, Toast.LENGTH_LONG).show();
                myDB.ThemPhieu(phieuDangKyModel);
            }
            edtSoNguoi.setText("");
            edtNgayDK.setText("");
            edtSoPhieu.setText("");
        } catch (NumberFormatException nfe) {
            String toastNull = ActivityThemPhieuDK.this.getResources().getString(R.string.alertNull);
            Toast.makeText(ActivityThemPhieuDK.this, toastNull, Toast.LENGTH_LONG).show();
        }
    }
    private void setControl() {
        edtSoPhieu = findViewById(R.id.nhapSoPhieu);
        edtNgayDK = findViewById(R.id.nhapNgayDangKy);
        edtSoNguoi = findViewById(R.id.nhapSoNguoi);
        spnMaKH = findViewById(R.id.nhapSpinnerMaKH);
        spnMaTour = findViewById(R.id.nhapSpinnerMaTour);
        btnThem = findViewById(R.id.add_button_phieudk);
    }
}
