package com.example.manulife.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

public class Transaction {

    public int accountID;
    public Date date;
    @Json(name = "activity")
    public List<Activity> activities;

    Transaction(Date date, @Json(name = "activity") List<Activity> activities) {
        this.accountID = accountID;
        this.date = date;
        this.activities = activities;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

