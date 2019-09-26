package com.sber.rupassword;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText mPasswordInput;
    private TextView mResultText;

    private String[] russians;
    private String[] latins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPasswordInput = findViewById(R.id.password_input);
        mResultText = findViewById(R.id.text_result);

        russians = getResources().getStringArray(R.array.russians);
        latins = getResources().getStringArray(R.array.latins);

        final PasswordHelper helper = new PasswordHelper(russians, latins);

        mPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mResultText.setText(helper.convert(s));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}
