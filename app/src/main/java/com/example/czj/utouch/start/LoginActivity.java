package com.example.czj.utouch.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czj.utouch.R;
import com.example.czj.utouch.main.MainPageActivity;
import com.example.czj.utouch.tools.EditTextSetter;
import com.example.czj.utouch.tools.HttpManager;
import com.example.czj.utouch.tools.LoadHttpCallBack;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String IDENTIFY_TO_REGISTER = "REGISTER";
    public static final String IDENTIFY_TO_RESET = "RESET";

    @BindView(R.id.login_toolbar)
    Toolbar loginToolbar;
    @BindView(R.id.login_usernameEditText)
    EditText loginUsernameEditText;
    @BindView(R.id.login_usernameHolder)
    TextInputLayout loginUsernameHolder;
    @BindView(R.id.login_passwordEditText)
    EditText loginPasswordEditText;
    @BindView(R.id.login_passwordHolder)
    TextInputLayout loginPasswordHolder;
    @BindView(R.id.login_registerTextView)
    TextView loginRegisterTextView;
    @BindView(R.id.login_forgetTextView)
    TextView loginForgetTextView;
    @BindView(R.id.login_loginButton)
    Button loginLoginButton;

    @OnClick({R.id.login_loginButton, R.id.login_forgetTextView, R.id.login_registerTextView})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.login_loginButton:
                /**
                 * @test
                 */
                startActivity(new Intent().setClass(LoginActivity.this, MainPageActivity.class));
                //clickLogin();
                break;
            case R.id.login_forgetTextView:
                clickForget();
                break;
            case R.id.login_registerTextView:
                clickRegister();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initToolbar();
        initEditText();
    }

    private void initToolbar() {
        setSupportActionBar(loginToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initEditText() {
        EditTextSetter.setEditText(loginUsernameEditText, loginUsernameHolder);
        EditTextSetter.setEditText(loginPasswordEditText, loginPasswordHolder);

        //auto fill
        Intent intent = getIntent();
        if (intent.getStringExtra(getString(R.string.IK_FLAG)) != null) {
            loginUsernameEditText.setText(intent.getStringExtra(getString(R.string.IK_USERNAME)));
            loginPasswordEditText.setText(intent.getStringExtra(getString(R.string.IK_PASSWORD)));
        }
    }

    private void clickLogin() {
        Intent intent = new Intent();
        boolean check = true;
        String username = loginUsernameEditText.getText().toString(),
                password = loginPasswordEditText.getText().toString();

        if (username.length() != 11) {
            check = false;
            EditTextSetter.setEditTextEnterError(loginUsernameHolder, getString(R.string.enterPhoneNumberError));
        } else if (password.length() < 6 || password.length() > 18) {
            check = false;
            EditTextSetter.setEditTextEnterError(loginPasswordHolder, getString(R.string.enterPasswordError));
        }

        if (check) {
            //request
            Map<String, String> params = new HashMap<>();
            //Todo: Hash code dealing
            params.put(getString(R.string.PK_USERNAME), username);
            params.put(getString(R.string.PK_PASSWORD), password);

            HttpManager.getInstance().postRequestByInterface(HttpManager.LOGIN_URL, params,
                    new LoadHttpCallBack<LoginBean>(LoginActivity.this) {
                        @Override
                        protected void onSuccess(Call call, Response response, LoginBean result) {
                            if (result.getCode() == 200) {
                                Log.d("Login", "Success: " + String.valueOf(result.getCode()));
                                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                                //Todo: record info and auto login
                            } else if (result.getCode() == 400) {
                                Log.d("Login", "Fail: " + result.getMessage());
                                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    private void clickForget() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, PhoneNumberIdentifyActivity.class);
        intent.putExtra(getString(R.string.IK_FLAG), IDENTIFY_TO_RESET);
        startActivity(intent);
    }

    private void clickRegister() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, PhoneNumberIdentifyActivity.class);
        intent.putExtra(getString(R.string.IK_FLAG), IDENTIFY_TO_REGISTER);
        startActivity(intent);
    }
}
