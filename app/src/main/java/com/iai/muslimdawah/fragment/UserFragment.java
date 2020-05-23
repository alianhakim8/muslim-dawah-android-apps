package com.iai.muslimdawah.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iai.muslimdawah.R;
import com.iai.muslimdawah.activity.LoginActivity;
import com.iai.muslimdawah.utils.DatabaseHelper;
import com.iai.muslimdawah.utils.SessionManager;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class UserFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;
    DatabaseHelper db;
    Button logOut;
    SessionManager sessionManager;
    TextView usernameProfile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        context = getActivity();
        Button logOut = view.findViewById(R.id.logout_button);
        db = new DatabaseHelper(getActivity());
        usernameProfile = view.findViewById(R.id.username_profile);
        sessionManager = new SessionManager(getContext());
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetails();
        String username = user.get(sessionManager.USERNAME);
        String password = user.get(sessionManager.PASSWORD);
        usernameProfile.setText(username);


        // logout Button onClick method
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE).
                        setTitleText("Do you want to log out ?").
                        setConfirmText("Yes").
                        setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                boolean updateSession = db.upgradeSession("empty", 1);
                                if (updateSession) {
                                    sessionManager.logOut();
                                    Intent login = new Intent(getContext(), LoginActivity.class);
                                    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(login);
                                    getActivity().finish();
                                }
                            }
                        }).setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                    }
                }).show();
            }
        });
        return view;
    }

    private void CloseActivity() {
        if (getActivity() != null) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            getActivity().finish();
        }
    }
}
