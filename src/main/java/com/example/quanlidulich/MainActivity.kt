package com.example.quanlidulich

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.example.quanlidulich.GiaoDien.Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed(
            { val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }, 1000
        )

    }
}