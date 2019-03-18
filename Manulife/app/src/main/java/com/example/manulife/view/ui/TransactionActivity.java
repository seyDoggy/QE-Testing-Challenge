package com.example.manulife.view.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.manulife.R;
import com.example.manulife.Util.Utils;
import com.example.manulife.model.Account;
import com.example.manulife.view.ui.transactions.TransactionsFragment;
import com.squareup.moshi.internal.Util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionActivity extends AppCompatActivity {
    private static Account account;
    @BindView(R.id.tv_account_num_trans)
    TextView tv_accountNumber;
    @BindView(R.id.tv_account_name)
    TextView tv_accountName;
    @BindView(R.id.tv_balance)
    TextView tv_accountBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setUpAccount();
        TransactionsFragment fragment = TransactionsFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, fragment)
                .commitNow();
        fragment.setAccount(account);
    }

    @Override
    protected void onStop() {
        super.onStop();
        onSaveInstanceState(Utils.session());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    private void setUpAccount() {
        account = getIntent().getParcelableExtra(getResources().getString(R.string.account_name));
        tv_accountBalance.setText(String.valueOf(account.getBalance()));
        tv_accountName.setText(account.getDisplayName());
        tv_accountNumber.setText(String.valueOf(account.getAccountNumber()));
    }
}
