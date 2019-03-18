package com.example.manulife.view.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.manulife.R;
import com.example.manulife.Util.Utils;
import com.example.manulife.view.ui.accounts.AccountsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fab_exit)
    FloatingActionButton exitButton;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Boolean flagSave = true;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPref.edit();

        exitButton.setOnClickListener(v -> {
            //exit and clear app data.
            delete();
            super.onBackPressed();
            flagSave = false;
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_frame, AccountsFragment.newInstance())
                .commitNow();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (flagSave) {
            onSaveInstanceState(Utils.session());
            save();
        }
    }

    private void delete() {
        sharedPref.edit().remove(getString(R.string.saved_active_state)).commit();
    }

    private void save() {
        editor.putString(getString(R.string.saved_active_state), Utils.ACTIVE_STATE);
        editor.commit();
    }

}
