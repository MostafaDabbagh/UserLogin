package com.example.userlogin.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.userlogin.R;
import com.example.userlogin.model.User;

public class SignUpActivity extends AppCompatActivity {

    public static final String TAG = "tag";
    public static final String EXTRA_KEY_USER = "user";
    Button mButtonSignUp;
    EditText mEditTextUsername;
    EditText mEditTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findViews();
        setListeners();
        initializeEditTexts();
    }

    private void findViews() {
        mButtonSignUp = findViewById(R.id.button_sign_up_in_sign_up);
        mEditTextUsername = findViewById(R.id.edit_text_username_in_sign_up);
        mEditTextPassword = findViewById(R.id.edit_text_password_in_sign_up);
    }

    private void setListeners() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areInputsValid()) {
                    String username = String.valueOf(mEditTextUsername.getText());
                    String password = String.valueOf(mEditTextPassword.getText());
                    User user = new User(username, password);
                    saveResult(user);
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this,
                            "Username and password cannot be empty",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void initializeEditTexts() {
        CharSequence username;
        CharSequence password;
        username = getIntent().getCharSequenceExtra(LoginActivity.EXTRA_KEY_USERNAME);
        password = getIntent().getCharSequenceExtra(LoginActivity.EXTRA_KEY_PASSWORD);
        mEditTextUsername.setText(username);
        mEditTextPassword.setText(password);
    }

    private void saveResult(User user) {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        intent.putExtra(EXTRA_KEY_USER, user);
        setResult(RESULT_OK, intent);
    }

    private boolean areInputsValid() {
        CharSequence username = mEditTextUsername.getText();
        CharSequence password = mEditTextPassword.getText();
        if (username == null || password == null || username.length() == 0 || password.length() == 0)
            return false;
        return true;
    }


}