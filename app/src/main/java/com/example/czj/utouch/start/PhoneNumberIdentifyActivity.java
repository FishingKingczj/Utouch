package com.example.czj.utouch.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.czj.utouch.R;
import com.example.czj.utouch.tools.EditTextSetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneNumberIdentifyActivity extends AppCompatActivity {
    boolean check = true;
    String username, code;

    @BindView(R.id.phoneNumberIdentify_toolbar)
    Toolbar phoneNumberIdentifyToolbar;
    @BindView(R.id.phoneNumberIdentify_usernameEditText)
    EditText phoneNumberIdentifyUsernameEditText;
    @BindView(R.id.phoneNumberIdentify_usernameHolder)
    TextInputLayout phoneNumberIdentifyUsernameHolder;
    @BindView(R.id.phoneNumberIdentify_codeEditText)
    EditText phoneNumberIdentifyCodeEditText;
    @BindView(R.id.phoneNumberIdentify_codeHolder)
    TextInputLayout phoneNumberIdentifyCodeHolder;
    @BindView(R.id.phoneNumberIdentify_sendButton)
    Button phoneNumberIdentifySendButton;
    @BindView(R.id.phoneNumberIdentify_nextStepButton)
    Button phoneNumberIdentifyNextStepButton;
    @BindView(R.id.phoneNumberIdentify_title)
    TextView phoneNumberIdentifyTitle;

    @OnClick({R.id.phoneNumberIdentify_sendButton, R.id.phoneNumberIdentify_nextStepButton})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.phoneNumberIdentify_sendButton:
                clickSend();
                break;
            case R.id.phoneNumberIdentify_nextStepButton:
                clickNext();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_identify);
        ButterKnife.bind(this);

        initToolbar();
        initTitle();
        initEditText();
    }

    private void initTitle() {
        Intent intent = getIntent();
        if (intent.getStringExtra(getString(R.string.IK_FLAG)).equals(LoginActivity.IDENTIFY_TO_RESET)) {
            phoneNumberIdentifyTitle.setText(getString(R.string.resetPassword));
        }
    }

    private void initToolbar() {
        setSupportActionBar(phoneNumberIdentifyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        phoneNumberIdentifyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEditText() {
        EditTextSetter.setEditText(phoneNumberIdentifyUsernameEditText, phoneNumberIdentifyUsernameHolder);
        EditTextSetter.setEditText(phoneNumberIdentifyCodeEditText, phoneNumberIdentifyCodeHolder);
    }

    private void clickSend() {
        username = phoneNumberIdentifyUsernameEditText.getText().toString();

        if (username.length() != 11) {
            check = false;
            EditTextSetter.setEditTextEnterError(phoneNumberIdentifyUsernameHolder, getString(R.string.enterPhoneNumberError));
        }

        if (check) {
            //Todo: request send message
        }
    }

    private void clickNext() {
        username = phoneNumberIdentifyUsernameEditText.getText().toString();
        code = phoneNumberIdentifyCodeEditText.getText().toString();

        if (username.length() != 11) {
            check = false;
            EditTextSetter.setEditTextEnterError(phoneNumberIdentifyUsernameHolder, getString(R.string.enterPhoneNumberError));
        } else if (code.length() != 4) {
            check = false;
            EditTextSetter.setEditTextEnterError(phoneNumberIdentifyCodeHolder, getString(R.string.enterIdentifyCodeError));
        }

        if (check) {
            //Todo: request check

            Intent intent = getIntent();
            intent.putExtra(getString(R.string.IK_USERNAME), username);
            intent.setClass(PhoneNumberIdentifyActivity.this, SetPasswordActivity.class);
            startActivity(intent);
        }
    }
}
