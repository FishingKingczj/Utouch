package com.example.czj.utouch.user;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.czj.utouch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySubscribeActivity extends AppCompatActivity {


    @BindView(R.id.user_subscribe_toolbar)
    Toolbar userSubscribeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subscribe);
        ButterKnife.bind(this);

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(userSubscribeToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
