package com.example.letthebeerchoosesyou;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerResultsAdapter extends RecyclerView.Adapter<RecyclerResultsAdapter.DataViewHolder> {

    private List<Data> dataList = new ArrayList<>();
    private ItemClickListener clickListener;

    public RecyclerResultsAdapter (ItemClickListener listener){
        this.clickListener = listener;
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView rowCategory;
        private TextView rowStyleFamily;
        private TextView rowStyle;
        protected ImageButton btnPlus;
        private ItemClickListener clickListener;

        public DataViewHolder (@NonNull View itemView, ItemClickListener listener) {
            super(itemView);
            rowCategory = itemView.findViewById(R.id.rowCategory);
            rowStyle = itemView.findViewById(R.id.rowStyle);
            rowStyleFamily = itemView.findViewById(R.id.rowStyleFamily);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            this.clickListener = listener;
        }

        public void setData (String category, String styleFamily, String style){
            rowStyle.setText(style);
            rowCategory.setText("Category: " + category);
            rowStyleFamily.setText("Style Family: " + styleFamily);
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recview_row, parent, false);
        return new DataViewHolder(rowView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        String style = dataList.get(position).getStyle();
        String styleFamily = dataList.get(position).getStyleFamily();
        String category = dataList.get(position).getCategory();
        holder.setData(category, styleFamily, style);
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(holder.getAdapterPosition(), dataList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData (List<Data> data){
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }

}
