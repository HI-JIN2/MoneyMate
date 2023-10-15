package com.ssu.moneymate.ui.main.fixed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ssu.moneymate.R;
import com.ssu.moneymate.databinding.ItemFixBinding;

import java.util.ArrayList;
import java.util.List;

public class FixedAdapter extends RecyclerView.Adapter<FixedAdapter.ViewHolder> {
    Context context;
    private List<FixedData> arrayList;
    private RoomDB database;

    public FixedAdapter(Context context, List<FixedData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FixedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fix, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FixedAdapter.ViewHolder holder, int position) {
        final FixedData data = arrayList.get(position);
        database = RoomDB.getInstance(context);

        holder.itemBinding.tvCategory.setText(data.getCategory());
        holder.itemBinding.tvPrice.setText(data.getMoney());

//        holder.bindItem(arrayList.get(position));
    }

    public void setFixedList(List<FixedData> list){
        this.arrayList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    ItemFixBinding itemBinding;
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemFixBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemBinding = ItemFixBinding.bind(itemView);
        }

    }
}
