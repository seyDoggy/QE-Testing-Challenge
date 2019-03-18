package com.example.manulife.view.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manulife.R;
import com.example.manulife.model.Activity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Activity> activities = new ArrayList<>();

    public TransactionAdapter(List<Activity> activityList) {
        this.activities.addAll(activityList);
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trans_item, parent, false);
        return new TransactionAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        Activity activity = activities.get(position);
        holder.tv_desc.setText(activity.getDescription());
        float value = (activity.getDeposit() == 0) ?
                -activity.getWithDrawl() : activity.getDeposit();
        holder.tv_trans_value.setText(String.valueOf(value));
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_desc;
        TextView tv_trans_value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_desc = itemView.findViewById(R.id.tv_trans_description);
            tv_trans_value = itemView.findViewById(R.id.tv_trans_value);
        }
    }
}
