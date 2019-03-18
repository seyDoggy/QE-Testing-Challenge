package com.example.manulife.view.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manulife.R;
import com.example.manulife.model.Activity;
import com.example.manulife.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private List<Transaction> transactions = new ArrayList<>();
    private Context context;

    public void setDateAdapter(List<Transaction> transactions, Context context) {
        this.transactions.addAll(transactions);
        this.context = context;
    }

    @NonNull
    @Override
    public DateAdapter.DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_item, parent, false);

        return new DateViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.DateViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.tv_date.setText(String.valueOf(transaction.date));
        List<Activity> activities = transaction.getActivities();
        TransactionAdapter transactionAdapter = new TransactionAdapter(activities);
        initRecyclerView(holder.recyclerView_child, transactionAdapter);
    }

    private void initRecyclerView(RecyclerView recyclerView, TransactionAdapter transactionAdapter) {
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(transactionAdapter);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class DateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_trans_date)
        TextView tv_date;
        @BindView(R.id.rv_child_transactions)
        RecyclerView recyclerView_child;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
