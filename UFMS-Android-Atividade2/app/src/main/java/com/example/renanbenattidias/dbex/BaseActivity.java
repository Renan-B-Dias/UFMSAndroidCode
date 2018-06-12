package com.example.renanbenattidias.dbex;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by renanbenattidias on 09/05/18.
 */

interface BaseActivityInterface {
    void findViews();
    void applyLayot();
}

public abstract class BaseActivity extends AppCompatActivity implements BaseActivityInterface {

    public Integer layoutReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutReference);
        findViews();
        applyLayot();
    }

    protected void showErrorToastWith(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
