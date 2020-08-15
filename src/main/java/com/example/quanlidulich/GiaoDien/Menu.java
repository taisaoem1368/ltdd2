package com.example.quanlidulich.GiaoDien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlidulich.R;

public class Menu extends AppCompatActivity {

    Button btnKH, btnPhieuDK, btnThe, btnTour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setControl();
        setEvent();

        //Đổi tên actionbar theo ngôn ngữ đã đổi:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trang chủ");
    }

    private void setControl() {
        btnKH = findViewById(R.id.dskh_button);
        btnPhieuDK = findViewById(R.id.dsPhieuDangKy);
        btnTour = findViewById(R.id.dsTourDL);
    }

    private void  setEvent() {
        btnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, ActivityDSKhachHang.class);
                startActivity(intent);
            }
        });

        btnTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, ActivityDSTour.class);
                startActivity(intent);
            }
        });
        btnPhieuDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, ActivityDSPhieuDK.class);
                startActivity(intent);
            }
        });
    }


}
