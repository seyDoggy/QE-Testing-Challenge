package com.example.manulife.view.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manulife.R;
import com.example.manulife.model.Account;
import com.example.manulife.view.ui.TransactionActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private List<Account> accounts = new ArrayList<>();
    private Context context;

    public void setAccounts(List<Account> accounts, Context context) {
        this.accounts.addAll(accounts);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.tv_accountBalance.setText(String.valueOf(account.getBalance()));
        holder.tv_accountName.setText(account.getDisplayName());
        holder.tv_accountNum.setText(String.valueOf(account.getAccountNumber()));
        holder.cv_account.setOnClickListener(v -> {
            //start new Activity with account data.
            openAccountDetails(account);
        });
    }

    private void openAccountDetails(Account account) {
        Intent detailIntent = new Intent(context, TransactionActivity.class);
        detailIntent.putExtra(context.getResources().getString(R.string.account_name), account);
        context.startActivity(detailIntent);
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_account_name)
        TextView tv_accountName;
        @BindView(R.id.tv_account_balance)
        TextView tv_accountBalance;
        @BindView(R.id.tv_account_number)
        TextView tv_accountNum;
        @BindView(R.id.cv_account)
        CardView cv_account;

        protected ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
