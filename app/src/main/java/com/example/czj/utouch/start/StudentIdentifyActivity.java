package com.example.czj.utouch.start;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.czj.utouch.R;
import com.example.czj.utouch.tools.EditTextSetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentIdentifyActivity extends AppCompatActivity {

    @BindView(R.id.studentIdentify_toolbar)
    Toolbar studentIdentifyToolbar;
    @BindView(R.id.studentIdentify_studentCardNumberEditText)
    EditText studentIdentifyStudentCardNumberEditText;
    @BindView(R.id.studentIdentify_studentCardNumberHolder)
    TextInputLayout studentIdentifyStudentCardNumberHolder;
    @BindView(R.id.studentIdentify_studentPasswordEditText)
    EditText studentIdentifyStudentPasswordEditText;
    @BindView(R.id.studentIdentify_studentPasswordHolder)
    TextInputLayout studentIdentifyStudentPasswordHolder;
    @BindView(R.id.studentIdentify_identifyCodeEditText)
    EditText studentIdentifyIdentifyCodeEditText;
    @BindView(R.id.studentIdentify_identifyCodeHolder)
    TextInputLayout studentIdentifyIdentifyCodeHolder;
    @BindView(R.id.studentIdentify_identifyCodeImageView)
    ImageView studentIdentifyIdentifyCodeImageView;
    @BindView(R.id.studentIdentify_nextStepButton)
    Button studentIdentifyNextStepButton;

    @OnClick({R.id.studentIdentify_identifyCodeImageView, R.id.studentIdentify_nextStepButton})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.studentIdentify_identifyCodeImageView:
                clickImage();
                break;
            case R.id.studentIdentify_nextStepButton:
                clickNext();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_identify);
        ButterKnife.bind(this);

        initToolbar();
        initEditText();
    }

    private void initToolbar() {
        setSupportActionBar(studentIdentifyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initEditText() {
        EditTextSetter.setEditText(studentIdentifyIdentifyCodeEditText, studentIdentifyIdentifyCodeHolder);
        EditTextSetter.setEditText(studentIdentifyStudentPasswordEditText, studentIdentifyStudentPasswordHolder);
        EditTextSetter.setEditText(studentIdentifyStudentCardNumberEditText, studentIdentifyStudentCardNumberHolder);
    }

    private void clickNext() {
        boolean check = true;
        String cardNumber = studentIdentifyStudentCardNumberEditText.getText().toString(),
                password = studentIdentifyStudentPasswordEditText.getText().toString(),
                code = studentIdentifyIdentifyCodeEditText.getText().toString();

        if (cardNumber.length() != 13) {
            check = false;
            EditTextSetter.setEditTextEnterError(studentIdentifyStudentCardNumberHolder, getString(R.string.enterStudentCardNumberError));
        } else if (password.length() != 6) {
            check = false;
            EditTextSetter.setEditTextEnterError(studentIdentifyStudentPasswordHolder, getString(R.string.enterStudentPasswordError));
        } else if (code.length() != 4) {
            check = false;
            EditTextSetter.setEditTextEnterError(studentIdentifyIdentifyCodeHolder, getString(R.string.enterIdentifyCodeError));
        }

        if (check) {
            //Todo: request check
            Intent intent = getIntent();
            //Todo: 本地是否需要记录学生卡？
            intent.setClass(StudentIdentifyActivity.this, RegisterInformationActivity.class);
            startActivity(intent);
        }
    }

    private void clickImage() {
        //Todo: refresh image
    }
}
