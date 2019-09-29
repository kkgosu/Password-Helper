package com.sber.rupassword;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

    PasswordHelper helper;

    private EditText mPasswordInput;
    private TextView mResultText;
    private TextView mResultGeneratedText;
    private TextView mPasswordLength;
    private ImageButton mCopyButton;
    private ImageButton mCopyButtonGenerated;
    private ImageView mComplexityInput;
    private ImageView mComplexityGenerated;
    private SeekBar mSeekBar;
    private CompoundButton mCaps;
    private CompoundButton mSymbols;
    private CompoundButton mNumbers;

    private String[] mRussians;
    private String[] mLatins;

    private int MIN_QUANTITY = 4;
    private int mSymbolsQuantity = MIN_QUANTITY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        mRussians = getResources().getStringArray(R.array.russians);
        mLatins = getResources().getStringArray(R.array.latins);
        helper = new PasswordHelper(mRussians, mLatins);

        mPasswordInput = findViewById(R.id.password_input);
        mResultText = findViewById(R.id.text_result);
        mResultGeneratedText = findViewById(R.id.text_result_generated);
        mPasswordLength = findViewById(R.id.password_length);
        mCopyButton = findViewById(R.id.copy_password_button);
        mCopyButtonGenerated = findViewById(R.id.copy_password_generated_button);
        mComplexityInput = findViewById(R.id.complexity_input);
        mSeekBar = findViewById(R.id.seekbar);
        mCaps = findViewById(R.id.check_caps);
        mSymbols = findViewById(R.id.check_symbols);
        mNumbers = findViewById(R.id.check_numbers);

        mCaps.setOnCheckedChangeListener(this);
        mSymbols.setOnCheckedChangeListener(this);
        mNumbers.setOnCheckedChangeListener(this);

        mCopyButton.setEnabled(false);
        mCopyButton.setOnClickListener(v -> copyToClipboard(mResultText));
        mCopyButtonGenerated.setOnClickListener(view -> copyToClipboard(mResultGeneratedText));

        setPlurals();
        generatePassword();

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

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mSymbolsQuantity = MIN_QUANTITY + i;
                setPlurals();
                generatePassword();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        generatePassword();
    }

    private void generatePassword() {
        mResultGeneratedText.setText(helper.generatePassword(mSymbolsQuantity, mCaps.isChecked(),
                mSymbols.isChecked(), mNumbers.isChecked()));
    }

    private void copyToClipboard(TextView password) {
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (manager != null) {
            manager.setPrimaryClip(ClipData.newPlainText(
                    getString(R.string.password), password.getText().toString()));
            Toast.makeText(MainActivity.this, R.string.toast_text_copied, Toast.LENGTH_SHORT).show();
        }
    }

    private void setPlurals() {
        String symbols = getResources().getQuantityString(
                R.plurals.symbols_count, mSymbolsQuantity, mSymbolsQuantity);
        mPasswordLength.setText(symbols);
    }
}
