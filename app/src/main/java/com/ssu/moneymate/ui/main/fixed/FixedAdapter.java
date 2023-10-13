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

public class FixedAdapter extends RecyclerView.Adapter<FixedAdapter.ViewHolder> {
    Context context;
    private ArrayList<FixedData> arrayList;

    public FixedAdapter(Context context, ArrayList<FixedData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FixedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fix, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FixedAdapter.ViewHolder holder, int position) {
        holder.bindItem(arrayList.get(position));
    }

    public void setFixedList(ArrayList<FixedData> list){
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

        void bindItem(FixedData item){
            //뷰 바인딩으로 이미 자식뷰들의 참조값들이 모두 연결되어 있음.
            itemBinding.tvEveryMonth.setText("매 월");
            itemBinding.tvWon.setText("원");
            itemBinding.tvCategory.setText(item.category);
            itemBinding.tvPrice.setText(String.valueOf(item.money));
        }
    }
}
