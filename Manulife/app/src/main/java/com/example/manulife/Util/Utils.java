package com.example.manulife.Util;

import android.os.Bundle;

public class Utils {
    public static final String ACTIVE_STATE = "ACTIVE_STATE";
    public static final String NEW_STATE = "ACTIVE_STATE";

    public static final Bundle session() {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.ACTIVE_STATE, Utils.ACTIVE_STATE);
        return bundle;
    }

    public static final Bundle newSession() {
        Bundle bundle = new Bundle();
        bundle.putString(Utils.NEW_STATE, Utils.NEW_STATE);
        return bundle;
    }
}
