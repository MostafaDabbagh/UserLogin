package com.example.userlogin.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userlogin.R;
import com.example.userlogin.model.User;
import com.williammora.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_KEY_USERNAME = "username";
    public static final String EXTRA_KEY_PASSWORD = "password";
    public static final int REQUEST_CODE_SIGN_UP = 0;
    public static final String BUNDLE_KEY_USER = "user bundle";

    User mUser;

    Button mButtonLogin;
    Button mButtonSignUp;
    EditText mEditTextUsername;
    EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            mUser = (User) savedInstanceState.getSerializable(BUNDLE_KEY_USER);
        }
        setContentView(R.layout.activity_login);
        findViews();
        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_KEY_USER, mUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null)
            return;
        else if (requestCode == REQUEST_CODE_SIGN_UP) {
            mUser = (User) data.getSerializableExtra(SignUpActivity.EXTRA_KEY_USER);
            initializeEditTexts();
        }
    }

    private void findViews() {
        mButtonLogin = findViewById(R.id.button_login);
        mButtonSignUp = findViewById(R.id.button_sign_up_in_login);
        mEditTextUsername = findViewById(R.id.edit_text_username_in_login);
        mEditTextPassword = findViewById(R.id.edit_text_password_in_login);
    }

    private void setListeners() {

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUser != null) {
                    Snackbar.with(LoginActivity.this)
                            .text("Signed up successfully!")
                            .show(LoginActivity.this);
                }
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignUpActivity();
            }
        });

    }

    private void startSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        intent.putExtra(EXTRA_KEY_USERNAME, mEditTextUsername.getText());
        intent.putExtra(EXTRA_KEY_PASSWORD, mEditTextPassword.getText());
        startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
    }

    private void initializeEditTexts() {
        CharSequence username = mUser.getUsername();
        CharSequence password = mUser.getPassword();
        mEditTextUsername.setText(username);
        mEditTextPassword.setText(password);
    }


}







