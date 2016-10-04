package com.aviato.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.aviato.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LauncherActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.desperate)
    public void onDesperateClicked() {
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("I need an email");
            return;
        }
        Intent intent = new Intent(this, MainActivity.class).putExtra(MainActivity.EXTRA_SIDE, 1);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.attractive)
    public void onAttractiveClicked() {
        if (TextUtils.isEmpty(email.getText())) {
            email.setError("I need an email");
            return;
        }
        Intent intent = new Intent(this, MainActivity.class).putExtra(MainActivity.EXTRA_SIDE, 0);
        startActivity(intent);
        finish();
    }
}
