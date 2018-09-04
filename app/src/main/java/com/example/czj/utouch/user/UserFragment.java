package com.example.czj.utouch.user;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.czj.utouch.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UserFragment extends Fragment {
    @BindView(R.id.user_head)
    LinearLayout userHead;
    @BindView(R.id.user_subscribe)
    LinearLayout userSubscribe;
    @BindView(R.id.user_activity)
    LinearLayout userActivity;
    @BindView(R.id.user_setting)
    LinearLayout userSetting;
    @BindView(R.id.user_logoff)
    LinearLayout userLogoff;
    Unbinder unbinder;

    @OnClick({R.id.user_head, R.id.user_subscribe, R.id.user_activity, R.id.user_setting, R.id.user_logoff})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.user_head:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.user_subscribe:
                startActivity(new Intent(getActivity(), MySubscribeActivity.class));
                break;
            case R.id.user_activity:
                break;
            case R.id.user_setting:
                break;
            case R.id.user_logoff:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
