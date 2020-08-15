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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlidulich.Databases.DBDuLich;
import com.example.quanlidulich.GiaoDien.ActivitySuaPhieuDK;
import com.example.quanlidulich.Model.PhieuDangKyModel;
import com.example.quanlidulich.R;

import java.util.ArrayList;

public class CustomAdapterPhieuDK extends RecyclerView.Adapter<CustomAdapterPhieuDK.MyViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
    private ArrayList<PhieuDangKyModel> data;
    private ArrayList<PhieuDangKyModel> dataArrayList;

    private DBDuLich myDB;

    public CustomAdapterPhieuDK(Activity activity, Context context, ArrayList<PhieuDangKyModel> data){
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
        View view = inflater.inflate(R.layout.recyclerview_item_phieudk, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final PhieuDangKyModel phieuDangKyModel = data.get(position);
        holder.tvID.setText(String.valueOf(phieuDangKyModel.getSttSoPhieuDK()));
        holder.tvSoPhieu.setText(String.valueOf(phieuDangKyModel.getSoPhieu()));
        holder.tvMaKH.setText(String.valueOf(phieuDangKyModel.getMaKH()));
        holder.tvMaTour.setText(String.valueOf(phieuDangKyModel.getMaTour()));
        holder.tvNgayDK.setText(String.valueOf(phieuDangKyModel.getNgayDK()));
        holder.tvSoNguoi.setText(String.valueOf(phieuDangKyModel.getSoNguoi()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivitySuaPhieuDK.class);
                intent.putExtra("id",Integer.valueOf(phieuDangKyModel.getSttSoPhieuDK()));
                intent.putExtra("soPhieu", String.valueOf(phieuDangKyModel.getSoPhieu()));
                intent.putExtra("maKH", String.valueOf(phieuDangKyModel.getMaKH()));
                intent.putExtra("maTour", String.valueOf(phieuDangKyModel.getMaTour()));
                intent.putExtra("ngayDK", String.valueOf(phieuDangKyModel.getNgayDK()));
                intent.putExtra("soNguoi", Integer.valueOf(phieuDangKyModel.getSoNguoi()));
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
                    ArrayList<PhieuDangKyModel> filteredList = new ArrayList<>();
                    for (PhieuDangKyModel phieuDangKyModel : dataArrayList) {

                        if (phieuDangKyModel.getSoPhieu().toLowerCase().contains(charString)) {

                            filteredList.add(phieuDangKyModel);
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
                data = (ArrayList<PhieuDangKyModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvSoPhieu, tvMaTour, tvMaKH, tvNgayDK, tvSoNguoi, tvID;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvIDPhieu);
            tvSoPhieu = itemView.findViewById(R.id.tvSoPhieu);
            tvMaKH = itemView.findViewById(R.id.tvPhieuDKMaKH);
            tvMaTour = itemView.findViewById(R.id.tvPhieuDKMaTour);
            tvNgayDK = itemView.findViewById(R.id.tvNgayDK);
            tvSoNguoi = itemView.findViewById(R.id.tvSoNguoi);
            mainLayout = itemView.findViewById(R.id.mainLayoutPhieuDK);

        }
    }
}
