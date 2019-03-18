package com.example.manulife.repository;

import com.example.manulife.model.Account;

import java.util.List;

public interface FetchAccountDataListener {
    void onError();

    void getAccountList(List<Account> accounts);
}
