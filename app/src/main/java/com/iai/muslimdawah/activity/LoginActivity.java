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
import com.iai.muslimdawah.utils.SessionManager;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    // UI references
    EditText username, password;
    //Database declaration
    DatabaseHelper db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();


        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        if (sessionManager.isLoggedIn()) {
            finish();
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(home);
            finish();
        }
        Button loginButton = findViewById(R.id.button_login);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);
        TextView mRegister = findViewById(R.id.register_textView);
        // database references
        db = new DatabaseHelper(this);


        // login button onClick
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLogin = username.getText().toString();
                String passLogin = password.getText().toString();

                if (userLogin.equals("") || passLogin.equals("")) {
                    Toast.makeText(LoginActivity.this, "Field Cannot Empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean login = db.checkLogin(userLogin, passLogin);
                    if (login) {

                        sessionManager.createSession(userLogin, passLogin);
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent main = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(main);
                    } else {
                        Toast.makeText(LoginActivity.this, "username or password is wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // adding click listener to register textView
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.bottom_in_animation_transition, R.anim.top_out_animation_transition);
            }
        });
    }
}
