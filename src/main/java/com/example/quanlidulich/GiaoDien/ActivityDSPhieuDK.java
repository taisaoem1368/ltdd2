package com.example.quanlidulich.GiaoDien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlidulich.Adapter.CustomAdapterPhieuDK;
import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.Model.PhieuDangKyModel;
import com.example.quanlidulich.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityDSPhieuDK extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button_phieudk;
    ImageView img_empty;
    TextView khongDuLieu;

    ArrayList<PhieuDangKyModel> data = new ArrayList<>();
    DBDuLich myDBDuLich = new DBDuLich(ActivityDSPhieuDK.this);
    CustomAdapterPhieuDK CustomAdapterPhieuDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsphieudk);
        setControl();
        setEvent();

        //Đổi tên actionbar theo ngôn ngữ đã đổi:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách phiếu đăng ký");

        luuDatabaseVaoArrays();
    }

    private void setEvent() {
        add_button_phieudk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDSPhieuDK.this, ActivityThemPhieuDK.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void luuDatabaseVaoArrays(){
        data = myDBDuLich.listPhieuDangKy();
        //
        if(data.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            CustomAdapterPhieuDK = new CustomAdapterPhieuDK(ActivityDSPhieuDK.this,this, data);
            recyclerView.setAdapter(CustomAdapterPhieuDK);
            recyclerView.setLayoutManager(new LinearLayoutManager(ActivityDSPhieuDK.this));

        }else {
            recyclerView.setVisibility(View.GONE);
            img_empty.setVisibility(View.VISIBLE);
            khongDuLieu.setVisibility(View.VISIBLE);
            Toast.makeText(ActivityDSPhieuDK.this,"Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        search(searchView);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (CustomAdapterPhieuDK!=null)
                    CustomAdapterPhieuDK.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewPhieuDK);
        add_button_phieudk = findViewById(R.id.add_button_phieudk);
        img_empty = findViewById(R.id.image_empty);
        khongDuLieu = findViewById(R.id.no_data);
    }
}
