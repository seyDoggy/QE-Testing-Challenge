package com.example.manulife.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

//@JsonClass(generateAdapter = true)
public class Account implements Parcelable {
    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
    private @Json(name = "id")
    int accountID;
    private @Json(name = "display_name")
    String displayName;
    private @Json(name = "account_number")
    long accountNumber;
    private float balance;

    protected Account(Parcel in) {
        accountID = in.readInt();
        displayName = in.readString();
        accountNumber = in.readLong();
        balance = in.readFloat();
    }

    public Account(@Json(name = "id") int accountID, @Json(name = "display_name") String displayName,
                   @Json(name = "account_number") long accountNumber, float balance) {
        this.accountID = accountID;
        this.displayName = displayName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(accountID);
        dest.writeString(displayName);
        dest.writeLong(accountNumber);
        dest.writeFloat(balance);
    }
}
