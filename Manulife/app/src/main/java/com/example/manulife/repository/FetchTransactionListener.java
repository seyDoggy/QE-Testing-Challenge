package com.example.manulife.repository;

import com.example.manulife.model.Transaction;

import java.util.List;

public interface FetchTransactionListener {
    void onError();

    void getTransactionList(List<Transaction> transactions);
}
