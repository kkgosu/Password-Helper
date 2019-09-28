package com.sber.rupassword;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText mPasswordInput;
    private TextView mResultText;
    private ImageButton mCopyButton;
    private ImageView mComplexityImage;

    private String[] mRussians;
    private String[] mLatins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPasswordInput = findViewById(R.id.password_input);
        mResultText = findViewById(R.id.text_result);
        mCopyButton = findViewById(R.id.copy_password_button);
        mComplexityImage = findViewById(R.id.complexity_input);

        for (int i = 0; i < 75; i++) {
            mComplexityImage.getDrawable().setLevel(i);
        }

        mRussians = getResources().getStringArray(R.array.russians);
        mLatins = getResources().getStringArray(R.array.latins);

        final PasswordHelper helper = new PasswordHelper(mRussians, mLatins);

        mCopyButton.setEnabled(false);
        mCopyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                if (manager != null) {
                    manager.setPrimaryClip(ClipData.newPlainText(
                            getString(R.string.password), mResultText.getText().toString()));
                    Toast.makeText(MainActivity.this, R.string.toast_text_copied, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPasswordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mResultText.setText(helper.convert(s));
                mCopyButton.setEnabled(s.length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }
}
