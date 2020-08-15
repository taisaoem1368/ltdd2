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
import com.example.quanlidulich.Model.TourModel;
import com.example.quanlidulich.R;

public class ActivityThemTour extends AppCompatActivity {
    EditText edtMaTour, edtLoTrinhTour, edtHanhTrinhTour, edtGiaTour;
    Button btnThemTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themtour);
        setControl();
        setEvent();

        //Đổi tên actionbar theo ngôn ngữ đã đổi:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thêm tour ");
    }

    private void setEvent() {
        btnThemTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTourVaoMang();
            }
        });
    }

    private void addTourVaoMang() {
        DBDuLich myDB = new DBDuLich(this);
        try {
            final String maTour = edtMaTour.getText().toString();
            final String hanhTrinhTour = edtHanhTrinhTour.getText().toString();
            final String loTrinhTour = edtLoTrinhTour.getText().toString();
            final Integer giaTour = Integer.parseInt(edtGiaTour.getText().toString());

            if(TextUtils.isEmpty(maTour) || TextUtils.isEmpty(hanhTrinhTour) || TextUtils.isEmpty(loTrinhTour)){
                String toastNull = ActivityThemTour.this.getResources().getString(R.string.alertNull);
                Toast.makeText(ActivityThemTour.this, toastNull, Toast.LENGTH_LONG).show();
            }
            else{
                String toastSuccess = ActivityThemTour.this.getResources().getString(R.string.alertSuccess);
                TourModel tourModel = new TourModel(maTour,hanhTrinhTour,loTrinhTour,giaTour);
                Toast.makeText(ActivityThemTour.this, toastSuccess, Toast.LENGTH_LONG).show();
                myDB.ThemTour(tourModel);
            }
            edtMaTour.setText("");
            edtHanhTrinhTour.setText("");
            edtLoTrinhTour.setText("");
            edtGiaTour.setText("");
        } catch (NumberFormatException nfe) {
            String toastNull = ActivityThemTour.this.getResources().getString(R.string.alertNull);
            Toast.makeText(ActivityThemTour.this, toastNull, Toast.LENGTH_LONG).show();
        }
    }
    private void setControl() {
        edtMaTour = findViewById(R.id.nhapMaTour);
        edtHanhTrinhTour = findViewById(R.id.nhapHanhTrinhTour);
        edtLoTrinhTour = findViewById(R.id.nhapLoTrinhTour);
        edtGiaTour = findViewById(R.id.nhapGiaTour);
        btnThemTour = findViewById(R.id.add_button_tour);
    }
}
