package com.example.quanlidulich.GiaoDien;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.quanlidulich.R;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText username, password;
    Context context;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();


    }

    private void setControl() {
        username = findViewById(R.id.usrusr);
        password = findViewById(R.id.pswrdd);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void  setEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Menu.class);
                startActivity(intent);

//                if (username.getText().toString().equals("admin")&& password.getText().toString().equals("admin")) {
//                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Login.this, ActivityDSKhachHang.class);
//                    startActivity(intent);
//                } else {
//
//                    Toast.makeText(Login.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
}
