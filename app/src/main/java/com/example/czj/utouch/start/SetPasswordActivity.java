package com.example.czj.utouch.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.czj.utouch.R;
import com.example.czj.utouch.tools.EditTextSetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.setPassword_toolbar)
    Toolbar setPasswordToolbar;
    @BindView(R.id.setPassword_passwordEditText)
    EditText setPasswordPasswordEditText;
    @BindView(R.id.setPassword_passwordHolder)
    TextInputLayout setPasswordPasswordHolder;
    @BindView(R.id.setPassword_checkPasswordEditText)
    EditText setPasswordCheckPasswordEditText;
    @BindView(R.id.setPassword_checkPasswordHolder)
    TextInputLayout setPasswordCheckPasswordHolder;
    @BindView(R.id.setPassword_nextStepButton)
    Button setPasswordNextStepButton;

    @OnClick(R.id.setPassword_nextStepButton)
    public void click(View view) {
        clickNext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);

        initToolbar();
        initEditText();
    }

    private void initToolbar() {
        setSupportActionBar(setPasswordToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initEditText() {
        EditTextSetter.setEditText(setPasswordPasswordEditText, setPasswordPasswordHolder);
        EditTextSetter.setEditText(setPasswordCheckPasswordEditText, setPasswordCheckPasswordHolder);
    }

    private void clickNext() {
        boolean check = true;
        String password = setPasswordPasswordEditText.getText().toString(),
                checkPassword = setPasswordCheckPasswordEditText.getText().toString();

        if (password.length() == 0) {
            check = false;
            EditTextSetter.setEditTextEnterError(setPasswordPasswordHolder, getString(R.string.enterPasswordError));
        } else if (password.length() < 6) {
            check = false;
            EditTextSetter.setEditTextEnterError(setPasswordPasswordHolder, getString(R.string.enterPasswordTooShortError));
        } else if (password.length() > 18) {
            check = false;
            EditTextSetter.setEditTextEnterError(setPasswordPasswordHolder, getString(R.string.enterPasswordTooLongError));
        } else if (!password.matches("^[a-zA-Z]\\w{5,17}$")) {
            check = false;
            EditTextSetter.setEditTextEnterError(setPasswordPasswordHolder, getString(R.string.enterPasswordCharacterError));
        } else if (!password.equals(checkPassword)) {
            check = false;
            EditTextSetter.setEditTextEnterError(setPasswordCheckPasswordHolder, getString(R.string.enterCheckPasswordError));
        }

        if (check) {
            Intent intent = getIntent();
            if (intent.getStringExtra(getString(R.string.IK_FLAG)).equals(LoginActivity.IDENTIFY_TO_REGISTER)) {
                intent.putExtra(getString(R.string.IK_PASSWORD), password);
                intent.setClass(SetPasswordActivity.this, StudentIdentifyActivity.class);
                startActivity(intent);
            } else {
                //Todo: request reset password and login
            }
        }
    }
}
