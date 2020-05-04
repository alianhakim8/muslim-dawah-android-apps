package com.iai.muslimdawah;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_sign_in);

        textInputEmail = findViewById(R.id.email_input);
        textInputPassword = findViewById(R.id.password_input);

        TextView to_sign_up = findViewById(R.id.to_sign_up);
        to_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private Boolean validateEmail() {
        String emailInput = Objects.requireNonNull(textInputEmail.getEditText()).getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void signInButtonOnClick(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        }

        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent(SignInActivity.this, StartActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
