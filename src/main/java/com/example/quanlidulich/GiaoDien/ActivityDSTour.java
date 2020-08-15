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

import com.example.quanlidulich.Adapter.CustomAdapterTour;
import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.Model.TourModel;
import com.example.quanlidulich.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ActivityDSTour extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView img_empty;
    TextView khongDuLieu;

    ArrayList<TourModel> data = new ArrayList<>();
    DBDuLich myDBDuLich = new DBDuLich(ActivityDSTour.this);
    CustomAdapterTour CustomAdapterTour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dstour);
        setControl();
        setEvent();

        //Đổi tên actionbar theo ngôn ngữ đã đổi:
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách tour");

        luuDatabaseVaoArrays();
    }

    private void setEvent() {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityDSTour.this, ActivityThemTour.class);
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
        data = myDBDuLich.listTourModel();
        //
        if(data.size() > 0){
            recyclerView.setVisibility(View.VISIBLE);
            CustomAdapterTour = new CustomAdapterTour(ActivityDSTour.this,this, data);
            recyclerView.setAdapter(CustomAdapterTour);
            recyclerView.setLayoutManager(new LinearLayoutManager(ActivityDSTour.this));

        }else {
            recyclerView.setVisibility(View.GONE);
            img_empty.setVisibility(View.VISIBLE);
            khongDuLieu.setVisibility(View.VISIBLE);
            Toast.makeText(ActivityDSTour.this,"Không có dữ liệu", Toast.LENGTH_SHORT).show();
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
                if (CustomAdapterTour!=null)
                    CustomAdapterTour.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recyclerViewDsTour);
        add_button = findViewById(R.id.add_button_dstour);
        img_empty = findViewById(R.id.image_empty);
        khongDuLieu = findViewById(R.id.no_data);
    }
}
