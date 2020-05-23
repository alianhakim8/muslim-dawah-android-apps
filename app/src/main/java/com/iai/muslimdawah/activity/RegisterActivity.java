package com.iai.muslimdawah.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.iai.muslimdawah.R;
import com.iai.muslimdawah.utils.DatabaseHelper;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {


    // declaration
    private EditText userName, userPassword, userEmail, userConfirmPassword;

    // Database
    DatabaseHelper db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_register);

        // UI References
        //userEmail = findViewById(R.id.email_register);
        userName = findViewById(R.id.username_register);
        userPassword = findViewById(R.id.password_register);
        userConfirmPassword = findViewById(R.id.confirm_password_register);
        Button registerButton = findViewById(R.id.register_button);
        TextView loginTextView = findViewById(R.id.login_textView);

        // Database References
        db = new DatabaseHelper(this);

        // register button onClick method
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String emailRegister = userEmail.getText().toString();
                String userRegister = userName.getText().toString();
                String passwordRegister = userPassword.getText().toString();
                String confirmPassRegister = userConfirmPassword.getText().toString();
                if (passwordRegister.equals(confirmPassRegister)) {
                    boolean register = db.insertUser(userRegister, passwordRegister);
                    if (register) {
                        Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                        db.saveData(userRegister, passwordRegister);
                        login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password don't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // login textView onClick method
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                loginActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginActivity);
                overridePendingTransition(R.anim.bottom_in_animation_transition, R.anim.top_out_animation_transition);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.bottom_in_animation_transition, R.anim.top_out_animation_transition);
    }
}
