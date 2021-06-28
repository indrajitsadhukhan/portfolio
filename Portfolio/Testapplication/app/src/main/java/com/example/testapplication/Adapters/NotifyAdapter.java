package com.example.testapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotifyAdapter extends RecyclerView.Adapter {
    String[]arrays;
    public NotifyAdapter(String[]arrays) {
        this.arrays=arrays;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notify, parent, false);
        return new ViewHolder(view);
    }
    int currposition=0;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.radioreminder.setText(arrays[position]);
        viewHolder.itemView.setOnClickListener(v -> {
            currposition=position;
            notifyDataSetChanged();
        });
        viewHolder.radioreminder.setChecked(position==currposition);
    }

    public int getCheck(){
        return currposition;
    }
    @Override
    public int getItemCount() {
        return arrays==null?0:arrays.length;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.radioreminder)
        RadioButton radioreminder;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}