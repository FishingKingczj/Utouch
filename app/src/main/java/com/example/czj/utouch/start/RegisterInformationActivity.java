package com.example.czj.utouch.start;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.czj.utouch.R;
import com.example.czj.utouch.tools.EditTextSetter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterInformationActivity extends AppCompatActivity {

    private SEX sex;

    @BindView(R.id.registerInfo_toolbar)
    Toolbar registerInfoToolbar;
    @BindView(R.id.registerInfo_nicknameEditText)
    EditText registerInfoNicknameEditText;
    @BindView(R.id.registerInfo_nicknameHolder)
    TextInputLayout registerInfoNicknameHolder;
    @BindView(R.id.registerInfo_majorEditText)
    EditText registerInfoMajorEditText;
    @BindView(R.id.registerInfo_majorHolder)
    TextInputLayout registerInfoMajorHolder;
    @BindView(R.id.registerInfo_gradeSpinner)
    Spinner registerInfoGradeSpinner;
    @BindView(R.id.registerInfo_gradeErrorText)
    TextView registerInfoGradeErrorText;
    @BindView(R.id.registerInfo_birthdayText)
    TextView registerInfoBirthdayText;
    @BindView(R.id.registerInfo_birthdayErrorText)
    TextView registerInfoBirthdayErrorText;
    @BindView(R.id.registerInfo_manRadioButton)
    RadioButton registerInfoManRadioButton;
    @BindView(R.id.registerInfo_womanRadioButton)
    RadioButton registerInfoWomanRadioButton;
    @BindView(R.id.registerInfo_sexRadioGroup)
    RadioGroup registerInfoSexRadioGroup;
    @BindView(R.id.registerInfo_nextStepButton)
    Button registerInfoNextStepButton;

    @OnClick(R.id.registerInfo_nextStepButton)
    public void click(View view) {
        clickNext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_information);
        ButterKnife.bind(this);

        initToolbar();
        initEditText();
        initRadioButton();
    }

    private void initToolbar() {
        setSupportActionBar(registerInfoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        registerInfoToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initEditText() {
        EditTextSetter.setEditText(registerInfoMajorEditText, registerInfoMajorHolder);
        EditTextSetter.setEditText(registerInfoNicknameEditText, registerInfoNicknameHolder);
    }

    private void initRadioButton() {
        registerInfoManRadioButton.setChecked(true);
        sex = SEX.MAN;
        registerInfoSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                int buttonId = radioGroup.getCheckedRadioButtonId();
                if (buttonId == R.id.registerInfo_manRadioButton)
                    sex = SEX.MAN;
                else sex = SEX.WOMAN;
            }
        });
    }

    private void initSpinner() {
        //Todo:
    }

    private void initDate() {
        //Todo:
    }

    private void clickNext() {
    }

    enum SEX {
        MAN, WOMAN
    }
}
