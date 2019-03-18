package com.example.manulife.view.ui.accounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.manulife.R;
import com.example.manulife.model.Account;
import com.example.manulife.repository.FetchAccountDataListener;
import com.example.manulife.repository.FetchData;
import com.example.manulife.view.ui.adapters.AccountsAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountsFragment extends Fragment implements FetchAccountDataListener {

    @BindView(R.id.rv_account)
    RecyclerView accountRecyclerView;
    @BindView(R.id.pb_loading)
    ProgressBar progressBar;
    private AccountsAdapter accountsAdapter = new AccountsAdapter();

    public static AccountsFragment newInstance() {
        return new AccountsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void fetchAccounts() {
        progressBar.setVisibility(View.VISIBLE);
        FetchData.getInstance().setFetchAccountDataListener(this);
        FetchData.getInstance().getListOfAccounts(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.accounts_fragment, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();
        fetchAccounts();
        return view;
    }

    private void initRecyclerView() {
        accountRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(accountRecyclerView.getContext(),
                layoutManager.getOrientation());
        accountRecyclerView.addItemDecoration(dividerItemDecoration);
        accountRecyclerView.setLayoutManager(layoutManager);
        accountRecyclerView.setAdapter(accountsAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onError() {
        //show error message
        progressBar.setVisibility(View.INVISIBLE);
        accountRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void getAccountList(List<Account> accounts) {
        //pass to recyclerview
        progressBar.setVisibility(View.INVISIBLE);
        accountsAdapter.setAccounts(accounts, getContext());
    }
}
