package com.example.czj.utouch.tools;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @Author: Czj
 * @Date: 2018/4/15 22:35
 * @Description:
 */

public class EditTextSetter {
    private EditTextSetter() {
    }

    public static void setEditText(final EditText editText, final TextInputLayout holder) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editText.getText().toString().length() > 0) {
                    holder.setError("");
                    holder.setErrorEnabled(false);
                }
            }
        });
    }

    public static void setEditTextEnterError(TextInputLayout holder, String errorInfo) {
        holder.setErrorEnabled(true);
        holder.setError(errorInfo);
    }
}
