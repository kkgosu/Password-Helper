package com.sber.rupassword;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PasswordFragment extends Fragment implements PasswordHolder {

    private TextView mPasswordView;
    private View mCopyButton;

    public PasswordFragment() {
        super(R.layout.fragment_password);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        mPasswordView = root.findViewById(R.id.text_result);
        mCopyButton = root.findViewById(R.id.copy_password_button);

        mCopyButton.setOnClickListener(v -> {
            ClipboardManager manager =
                    (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            if (manager != null) {
                manager.setPrimaryClip(ClipData.newPlainText(
                        getString(R.string.password), mPasswordView.getText().toString()));
                Toast.makeText(requireContext(), R.string.toast_text_copied, Toast.LENGTH_SHORT)
                     .show();
            }
        });

        mCopyButton.setEnabled(mPasswordView.getText().length() > 0);

        return root;
    }

    @Override
    public void showPassword(String password) {
        mPasswordView.setText(password);
    }

}
