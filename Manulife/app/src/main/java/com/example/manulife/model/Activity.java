package com.example.manulife.model;

import com.squareup.moshi.Json;

import java.util.Date;

public class Activity {
    @Json(name = "id")
    int activityID;
    Date date;
    String description;
    @Json(name = "withdrawal_amount")
    float withDrawl;
    @Json(name = "deposit_amount")
    float deposit;
    float balance;
    @Json(name = "transaction_uid")
    String transactionUUID;

    Activity(@Json(name = "id") int activityID, Date date, String description,
             @Json(name = "deposit_amount") float deposit,
             @Json(name = "withdrawal_amount") float withDrawl, float balance,
             @Json(name = "transaction_uid") String transactionUUID) {
        this.activityID = activityID;
        this.date = date;
        this.deposit = deposit;
        this.description = description;
        this.balance = balance;
        this.transactionUUID = transactionUUID;
        this.withDrawl = withDrawl;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getWithDrawl() {
        return withDrawl;
    }

    public void setWithDrawl(float withDrawl) {
        this.withDrawl = withDrawl;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getTransactionUUID() {
        return transactionUUID;
    }

    public void setTransactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
    }
}
