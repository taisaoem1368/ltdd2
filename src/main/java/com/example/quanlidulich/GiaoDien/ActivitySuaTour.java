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
import com.example.quanlidulich.Model.TourModel;
import com.example.quanlidulich.R;

public class ActivitySuaTour extends AppCompatActivity {
    EditText edtMaTour, edtLoTrinhTour, edtHanhTrinhTour, edtGiaTour;
    Button btnLuuTour, btnXoaTour;

    TourModel tourModel = new TourModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suatour);
        setControl();
        setEvent();
        layDuLieuTour();

        //Lấy tên cho trang details
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Sửa tour " + "" +  tourModel.getMaTour());
        }
    }

    private void setEvent() {
        btnLuuTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                suaTourVaoMang();
            }
        });

        btnXoaTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmXoa();
            }
        });
    }

    private void suaTourVaoMang() {
        DBDuLich myDB =  new DBDuLich(this);
        try {
            final String maTour = edtMaTour.getText().toString();
            final String loTrinh = edtLoTrinhTour.getText().toString();
            final String hanhTrinh = edtHanhTrinhTour.getText().toString();
            final Integer giaTour = Integer.parseInt(edtGiaTour.getText().toString());

            if(TextUtils.isEmpty(maTour) || TextUtils.isEmpty(loTrinh) || TextUtils.isEmpty(hanhTrinh)){
                String toastNull = ActivitySuaTour.this.getResources().getString(R.string.alertNull);
                Toast.makeText(ActivitySuaTour.this, toastNull, Toast.LENGTH_LONG).show();
            }
            else{
                String toastSuccess = ActivitySuaTour.this.getResources().getString(R.string.alertSuccess);
                myDB.SuaTour(new TourModel(tourModel.getSttTour(),maTour,loTrinh,hanhTrinh, giaTour));
                Toast.makeText(ActivitySuaTour.this, toastSuccess, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException nfe) {
            String toastNull = ActivitySuaTour.this.getResources().getString(R.string.alertNull);
            Toast.makeText(ActivitySuaTour.this, toastNull, Toast.LENGTH_LONG).show();
        }

    }

    //Lấy dữ liệu đổ vào trang sửa khi click vào item
    void layDuLieuTour(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("maTour") &&
                getIntent().hasExtra("loTrinh") &&
                getIntent().hasExtra("hanhTrinh") &&
                getIntent().hasExtra("giaTour")){
            //Getting Data from Intent
            int id = -1;
            tourModel.setSttTour(getIntent().getIntExtra("id",id));
            tourModel.setMaTour(getIntent().getStringExtra("maTour"));
            tourModel.setLoTrinh(getIntent().getStringExtra("loTrinh"));
            tourModel.setHanhTrinh(getIntent().getStringExtra("hanhTrinh"));
            tourModel.setGiaTour(getIntent().getIntExtra("giaTour", -1));

            //Setting Intent Data
            edtMaTour.setText(tourModel.getMaTour());
            edtLoTrinhTour.setText(tourModel.getLoTrinh());
            edtHanhTrinhTour.setText(tourModel.getHanhTrinh());
            edtGiaTour.setText(String.valueOf(tourModel.getGiaTour()));
        } else {
            String toastMessage = ActivitySuaTour.this.getResources().getString(R.string.noData);
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    void confirmXoa() {
        String toastTittle = ActivitySuaTour.this.getResources().getString(R.string.confirmDeleteTitle);
        String toastMessage = ActivitySuaTour.this.getResources().getString(R.string.confirmDeleteMessage);
        String toastYes = ActivitySuaTour.this.getResources().getString(R.string.confirmYes);
        String toastNo = ActivitySuaTour.this.getResources().getString(R.string.confirmNo);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(toastTittle + tourModel.getMaTour());
        builder.setMessage(toastMessage + tourModel.getMaTour());
        builder.setPositiveButton(toastYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBDuLich myDB = new DBDuLich(ActivitySuaTour.this);
                myDB.XoaTour(tourModel);
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
        edtMaTour = findViewById(R.id.suaMaTour);
        edtLoTrinhTour = findViewById(R.id.suaLoTrinhTour);
        edtHanhTrinhTour = findViewById(R.id.suaHanhTrinhTour);
        edtGiaTour = findViewById(R.id.suaGiaTour);
        btnLuuTour = findViewById(R.id.save_button_tour);
        btnXoaTour = findViewById(R.id.delete_button_tour);
    }
}
