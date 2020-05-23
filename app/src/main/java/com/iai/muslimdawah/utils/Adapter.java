package com.iai.muslimdawah.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.iai.muslimdawah.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Data> dataList;

    public Adapter(FragmentActivity activity, List<Data> dataList) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title = itemView.findViewById(R.id.recyclerViewTitle);
        TextView desc = itemView.findViewById(R.id.recyclerViewDesc);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Data pu = dataList.get(position);

        holder.title.setText(pu.getName());
        holder.desc.setText(pu.getDesc());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
