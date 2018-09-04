package com.example.czj.utouch.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.czj.utouch.R;
import com.example.czj.utouch.user.UserFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageActivity extends AppCompatActivity {

    private FragmentManager fragment;
    private Fragment user;

    @BindView(R.id.main_content)
    FrameLayout content;
    @BindView(R.id.main_bottomBar)
    BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);

        setFragment();
        setBottomBar();
    }

    private void setFragment() {
        fragment = getFragmentManager();
        FragmentTransaction transaction = fragment.beginTransaction();

        user = new UserFragment();

        //transaction.replace(R.id.main_content, user);
        transaction.commit();
    }

    private void setBottomBar() {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentTransaction transaction = fragment.beginTransaction();
                switch (tabId) {
                    case R.id.tab_subscribe:
                        break;
                    case R.id.tab_message:
                        break;
                    case R.id.tab_add:
                        break;
                    case R.id.tab_discover:
                        break;
                    case R.id.tab_user:
                        transaction.replace(R.id.main_content, user);
                        break;
                }
                transaction.commit();
            }
        });
    }
}
