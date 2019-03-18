package com.example.manulife.repository;

import android.content.Context;

import com.example.manulife.model.Account;
import com.example.manulife.model.Transaction;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public class FetchData {

    private static final String LIST_OF_ACCOUNTS = "listOfAccounts.json";
    private static final String CHEQUING_ACCOUNT = "chequingAccount.json";
    private static final String SAVINGS_ACCOUNT = "savingsAccount.json";
    private static final String TFSA_ACCOUNT = "TfsaAccount.json";
    private static final int TFSA_ACCOUNT_ID = 19;
    private static final int CHEQUING_ACCOUNT_ID = 10;
    private static final int SAVINGS_ACCOUNT_ID = 12;
    private static FetchData singleton = new FetchData();
    private FetchAccountDataListener fetchAccountDataListener = null;
    private FetchTransactionListener fetchTransactionListener = null;

    public static FetchData getInstance() {
        return singleton;
    }

    public void setFetchAccountDataListener(FetchAccountDataListener fetchAccountDataListener) {
        this.fetchAccountDataListener = fetchAccountDataListener;
    }

    public void setFetchTransactionDataListener(FetchTransactionListener fetchTransactionDataListener) {
        this.fetchTransactionListener = fetchTransactionDataListener;
    }

    public void getListOfAccounts(Context context) {
        List<Account> list = moshiAccountListSetup(LIST_OF_ACCOUNTS, context);
        fetchAccountDataListener.getAccountList(list);
    }

    public void getListOfTransactions(Account account, Context context) {
        List<Transaction> list = null;
        String accountName = getAccountName(account);
        list = moshiTransactionSetup(accountName, context);
        fetchTransactionListener.getTransactionList(list);
    }

    private String getAccountName(Account account) {
        String accountName = "";
        switch (account.getAccountID()) {
            case TFSA_ACCOUNT_ID:
                accountName = TFSA_ACCOUNT;
                break;
            case CHEQUING_ACCOUNT_ID:
                accountName = CHEQUING_ACCOUNT;
                break;
            case SAVINGS_ACCOUNT_ID:
                accountName = SAVINGS_ACCOUNT;
                break;
        }
        return accountName;
    }

    private List<Account> moshiAccountListSetup(String jsonFileName, Context context) {
        Moshi moshi = new Moshi.Builder().build();
        Type type = Types.newParameterizedType(List.class, Account.class);
        JsonAdapter<List<Account>> adapter = moshi.adapter(type);
        List<Account> accounts = null;
        try {
            accounts = adapter.fromJson(loadJSONFromAsset(jsonFileName, context));
        } catch (IOException e) {
            e.printStackTrace();
            fetchAccountDataListener.onError();
        }
        return accounts;
    }

    private List<Transaction> moshiTransactionSetup(String jsonFileName, Context context) {
        Type transactionAdapter = Types.newParameterizedType(List.class, Transaction.class);
        Moshi moshi = new Moshi.Builder()
                .add(Date.class, new Rfc3339DateJsonAdapter())
                .build();

        List<Transaction> transactionList = null;
        JsonAdapter<Transaction> jsonAdapter = moshi.adapter(transactionAdapter);
        try {
            transactionList = (List<Transaction>) jsonAdapter.fromJson(loadJSONFromAsset(jsonFileName, context));
        } catch (IOException e) {
            e.printStackTrace();
            fetchTransactionListener.onError();
        }
        return transactionList;
    }


    private String loadJSONFromAsset(String jsonFileName, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
