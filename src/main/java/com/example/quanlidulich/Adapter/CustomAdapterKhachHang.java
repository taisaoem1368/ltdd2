package com.example.quanlidulich.Adapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.GiaoDien.ActivitySuaKhachHang;
import com.example.quanlidulich.R;
import com.example.quanlidulich.Model.KhachHangModel;
public class CustomAdapterKhachHang extends RecyclerView.Adapter<CustomAdapterKhachHang.MyViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
    private ArrayList<KhachHangModel> data;
    private ArrayList<KhachHangModel> dataArrayList;

    private DBDuLich myDB;

    public CustomAdapterKhachHang(Activity activity, Context context, ArrayList<KhachHangModel> data){
        this.activity = activity;
        this.context = context;
        this.data = data;
        this.dataArrayList=data;
        myDB = new DBDuLich(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item_kh, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final KhachHangModel kh = data.get(position);
        holder.tvID.setText(String.valueOf(kh.getSttKH()));
        holder.tvMaKH.setText(String.valueOf(kh.getMaKH()));
        holder.tvTenKH.setText(String.valueOf(kh.getTenKH()));
        holder.tvDiaChi.setText(String.valueOf(kh.getDiaChi()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivitySuaKhachHang.class);
                intent.putExtra("id",Integer.valueOf(kh.getSttKH()));
                intent.putExtra("maKH", String.valueOf(kh.getMaKH()));
                intent.putExtra("tenKH", String.valueOf(kh.getTenKH()));
                intent.putExtra("diaChi", String.valueOf(kh.getDiaChi()));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    data = dataArrayList;
                } else {
                    ArrayList<KhachHangModel> filteredList = new ArrayList<>();
                    for (KhachHangModel kh : dataArrayList) {

                        if (kh.getMaKH().toLowerCase().contains(charString)) {

                            filteredList.add(kh);
                        }
                    }
                    data = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                data = (ArrayList<KhachHangModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaKH, tvTenKH, tvDiaChi, tvID;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvIDKH);
            tvMaKH = itemView.findViewById(R.id.tvMaKH);
            tvTenKH = itemView.findViewById(R.id.tvTenKH);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            mainLayout = itemView.findViewById(R.id.mainLayoutKH);

        }
    }
}
