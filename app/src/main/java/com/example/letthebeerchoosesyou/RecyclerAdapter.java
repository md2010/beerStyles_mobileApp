package com.example.letthebeerchoosesyou;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewHolder> {

    private List<Data> dataList = new ArrayList<>();
    private ItemClickListener clickListener;


    public RecyclerAdapter (ItemClickListener listener){
        this.clickListener = listener;

    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView rowCategory;
        private TextView rowStyleFamily;
        private TextView rowStyle;
        protected ImageButton btnRemove;
        private ItemClickListener clickListener;
        //private ItemLongClickListener longClickListener;
        private CardView cardView;
        private int selectedPosition = -1;

        public DataViewHolder (@NonNull View itemView, ItemClickListener listener
                              ) {
            super(itemView);
            rowCategory = itemView.findViewById(R.id.rowCategory);
            rowStyle = itemView.findViewById(R.id.rowStyle);
            rowStyleFamily = itemView.findViewById(R.id.rowStyleFamily);
            btnRemove = itemView.findViewById(R.id.btnRemove);
            this.cardView = itemView.findViewById(R.id.cardView);
            this.clickListener = listener;
            //this.longClickListener = listener1;
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    return true;
                }
            });
        }

        public void setData (String category, String styleFamily, String style){
            rowStyle.setText(style);
            rowCategory.setText("Category: " + category);
            rowStyleFamily.setText("Style Family: " + styleFamily);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recview_beer_list_row, parent, false);
        final DataViewHolder dataViewHolder = new DataViewHolder(rowView, clickListener);
        return dataViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final String style = dataList.get(position).getStyle();
        final String styleFamily = dataList.get(position).getStyleFamily();
        final String category = dataList.get(position).getCategory();
        holder.setData(category, styleFamily, style);
        if (holder.selectedPosition == position) {
            holder.cardView.setBackgroundColor(Color.parseColor("#89cfd8dc"));
        }
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
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

    public void removeCell(int position){
        dataList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dataList.size());
    }

}


