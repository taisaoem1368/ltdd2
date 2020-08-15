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
import com.example.quanlidulich.GiaoDien.ActivitySuaTour;
import com.example.quanlidulich.Model.TourModel;
import com.example.quanlidulich.R;

import java.util.ArrayList;

public class CustomAdapterTour extends RecyclerView.Adapter<CustomAdapterTour.MyViewHolder> implements Filterable {
    private Context context;
    private Activity activity;
    private ArrayList<TourModel> data;
    private ArrayList<TourModel> dataArrayList;

    private DBDuLich myDB;

    public CustomAdapterTour(Activity activity, Context context, ArrayList<TourModel> data){
        this.activity = activity;
        this.context = context;
        this.data = data;
        this.dataArrayList=data;
        myDB = new DBDuLich(context);
    }

    @NonNull
    @Override
    public CustomAdapterTour.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item_dstour, parent, false);
        return new CustomAdapterTour.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterTour.MyViewHolder holder, final int position) {
        final TourModel tourModel = data.get(position);
        holder.tvID.setText(String.valueOf(tourModel.getSttTour()));
        holder.tvMaTour.setText(String.valueOf(tourModel.getMaTour()));
        holder.tvLoTrinh.setText(String.valueOf(tourModel.getLoTrinh()));
        holder.tvHanhTrinh.setText(String.valueOf(tourModel.getHanhTrinh()));
        holder.tvGiaTour.setText(String.valueOf(tourModel.getGiaTour()));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivitySuaTour.class);
                intent.putExtra("id",Integer.valueOf(tourModel.getSttTour()));
                intent.putExtra("maTour", String.valueOf(tourModel.getMaTour()));
                intent.putExtra("loTrinh", String.valueOf(tourModel.getLoTrinh()));
                intent.putExtra("hanhTrinh", String.valueOf(tourModel.getHanhTrinh()));
                intent.putExtra("giaTour", Integer.valueOf(tourModel.getGiaTour()));
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
                    ArrayList<TourModel> filteredList = new ArrayList<>();
                    for (TourModel tourModel : dataArrayList) {

                        if (tourModel.getMaTour().toLowerCase().contains(charString)) {

                            filteredList.add(tourModel);
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
                data = (ArrayList<TourModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTour, tvLoTrinh, tvHanhTrinh, tvGiaTour, tvID;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvIDTour);
            tvMaTour = itemView.findViewById(R.id.tvMaTour);
            tvLoTrinh = itemView.findViewById(R.id.tvLoTrinh);
            tvHanhTrinh = itemView.findViewById(R.id.tvHanhTrinh);
            tvGiaTour = itemView.findViewById(R.id.tvGiaTour);
            mainLayout = itemView.findViewById(R.id.mainLayoutTour);

        }
    }
}
