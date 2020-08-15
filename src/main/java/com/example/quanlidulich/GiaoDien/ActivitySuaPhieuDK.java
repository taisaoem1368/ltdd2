package com.example.quanlidulich.GiaoDien;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.Model.KhachHangModel;
import com.example.quanlidulich.Model.PhieuDangKyModel;
import com.example.quanlidulich.R;

import java.util.ArrayList;

public class ActivitySuaPhieuDK extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText edtSoPhieu, edtNgayDK, edtSoNguoi;
    Spinner spnMaKH, spnMaTour;
    public DBDuLich myDB;
    private String maKH = "";
    private String maTour = "";
    private ArrayList<String> arrKH = new ArrayList<>();
    private ArrayList<String> arrTour = new ArrayList<>();
    Button btnLuu, btnXoa;

    PhieuDangKyModel phieuDangKyModel = new PhieuDangKyModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suaphieudk);
        setControl();
        setEvent();
        layDuLieuPhieu();

        //Lấy tên cho trang details
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Sửa phiếu " + " " +  phieuDangKyModel.getSoPhieu());
        }
    }

    private void setEvent() {
        spnMaKH.setOnItemSelectedListener(this);
        spnMaTour.setOnItemSelectedListener(this);
        loadSpinnerData();
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaPhieuVaomang();
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmXoa();
            }
        });
    }

    private void loadSpinnerData() {
        myDB = new DBDuLich(getApplicationContext());
        //Set giá trị cho spinner Mã KH
        arrKH = myDB.getAllMaKH();
        ArrayAdapter<String> adapterMaKH = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrKH);
        adapterMaKH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaKH.setAdapter(adapterMaKH);

        //Set giá trị cho spinner Mã Tour
        arrTour = myDB.getAllMaTour();
        ArrayAdapter<String> adapterMaTour = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrTour);
        adapterMaKH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMaTour.setAdapter(adapterMaTour);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.suaSpinnerMaKH) {
            maKH = parent.getItemAtPosition(position).toString();
        } else if (spinner.getId() == R.id.suaSpinnerMaTour) {
            maTour = parent.getItemAtPosition(position).toString();
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    private void suaPhieuVaomang() {
        DBDuLich myDB =  new DBDuLich(this);
        try {
            final String soPhieu = edtSoPhieu.getText().toString();
            final String ngayDangKy = edtNgayDK.getText().toString();
            final Integer soNguoi = Integer.parseInt(edtSoNguoi.getText().toString());

            if(TextUtils.isEmpty(soPhieu) || TextUtils.isEmpty(ngayDangKy)){
                String toastNull = ActivitySuaPhieuDK.this.getResources().getString(R.string.alertNull);
                Toast.makeText(ActivitySuaPhieuDK.this, toastNull, Toast.LENGTH_LONG).show();
            }
            else{
                String toastSuccess = ActivitySuaPhieuDK.this.getResources().getString(R.string.alertSuccess);
                myDB.SuaPhieu(new PhieuDangKyModel(phieuDangKyModel.getSttSoPhieuDK(),soPhieu, maKH, maTour,ngayDangKy,soNguoi));
                Toast.makeText(ActivitySuaPhieuDK.this, toastSuccess, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException nfe) {
            String toastNull = ActivitySuaPhieuDK.this.getResources().getString(R.string.alertNull);
            Toast.makeText(ActivitySuaPhieuDK.this, toastNull, Toast.LENGTH_LONG).show();
        }
    }

    //Lấy dữ liệu đổ vào trang sửa khi click vào item
    void layDuLieuPhieu(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("soPhieu") &&
                getIntent().hasExtra("maKH") &&
                getIntent().hasExtra("maTour") &&
                getIntent().hasExtra("ngayDK") &&
                getIntent().hasExtra("soNguoi")){
            //Getting Data from Intent
            int id = -1;
            phieuDangKyModel.setSttSoPhieuDK(getIntent().getIntExtra("id",id));
            phieuDangKyModel.setSoPhieu(getIntent().getStringExtra("soPhieu"));
            phieuDangKyModel.setMaKH(getIntent().getStringExtra("maKH"));
            phieuDangKyModel.setMaTour(getIntent().getStringExtra("maTour"));
            phieuDangKyModel.setNgayDK(getIntent().getStringExtra("ngayDK"));
            phieuDangKyModel.setSoNguoi(getIntent().getIntExtra("soNguoi", -1));

            //Setting Intent Data
            edtSoPhieu.setText(phieuDangKyModel.getSoPhieu());
            edtNgayDK.setText(phieuDangKyModel.getNgayDK());
            edtSoNguoi.setText(String.valueOf(phieuDangKyModel.getSoNguoi()));

            spnMaKH.setSelection(arrKH.indexOf(phieuDangKyModel.getMaKH()));
            spnMaTour.setSelection(arrTour.indexOf(phieuDangKyModel.getMaTour()));
        } else {
            String toastMessage = ActivitySuaPhieuDK.this.getResources().getString(R.string.noData);
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    void confirmXoa() {
        String toastTittle = ActivitySuaPhieuDK.this.getResources().getString(R.string.confirmDeleteTitle);
        String toastMessage = ActivitySuaPhieuDK.this.getResources().getString(R.string.confirmDeleteMessage);
        String toastYes = ActivitySuaPhieuDK.this.getResources().getString(R.string.confirmYes);
        String toastNo = ActivitySuaPhieuDK.this.getResources().getString(R.string.confirmNo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(toastTittle + phieuDangKyModel.getSoPhieu());
        builder.setMessage(toastMessage + phieuDangKyModel.getSoPhieu());
        builder.setPositiveButton(toastYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBDuLich myDB = new DBDuLich(ActivitySuaPhieuDK.this);
                myDB.XoaPhieu(phieuDangKyModel);
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
        edtSoPhieu = findViewById(R.id.suaSoPhieu);
        edtNgayDK = findViewById(R.id.suaNgayDangKy);
        edtSoNguoi = findViewById(R.id.suaSoNguoi);
        spnMaKH = findViewById(R.id.suaSpinnerMaKH);
        spnMaTour = findViewById(R.id.suaSpinnerMaTour);
        btnLuu = findViewById(R.id.save_button_phieudk);
        btnXoa = findViewById(R.id.delete_button_phieudk);
    }
}
