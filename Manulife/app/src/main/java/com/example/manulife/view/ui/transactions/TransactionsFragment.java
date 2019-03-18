package com.example.manulife.view.ui.transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.manulife.R;
import com.example.manulife.model.Account;
import com.example.manulife.model.Transaction;
import com.example.manulife.repository.FetchData;
import com.example.manulife.repository.FetchTransactionListener;
import com.example.manulife.view.ui.adapters.DateAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsFragment extends Fragment implements FetchTransactionListener {

    @BindView(R.id.rv_parent_dates)
    RecyclerView rv_parent_dates;

    @BindView(R.id.pb_trans_loading)
    ProgressBar pb_loading;
    private Account account;

    private DateAdapter dateAdapter = new DateAdapter();

    public static TransactionsFragment newInstance() {
        return new TransactionsFragment();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transactions_fragment, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        fetchTransactionsForAccount(account);
        return view;
    }

    private void initRecyclerView() {
        rv_parent_dates.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_parent_dates.getContext(),
                layoutManager.getOrientation());
        rv_parent_dates.addItemDecoration(dividerItemDecoration);
        rv_parent_dates.setLayoutManager(layoutManager);
        rv_parent_dates.setAdapter(dateAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void fetchTransactionsForAccount(Account account) {
        FetchData.getInstance().setFetchTransactionDataListener(this);
        FetchData.getInstance().getListOfTransactions(account, getContext());
    }


    @Override
    public void onError() {
        pb_loading.setVisibility(View.INVISIBLE);
        rv_parent_dates.setVisibility(View.INVISIBLE);
    }

    @Override
    public void getTransactionList(List<Transaction> transactions) {
        pb_loading.setVisibility(View.INVISIBLE);
        dateAdapter.setDateAdapter(transactions, getContext());
    }

}
