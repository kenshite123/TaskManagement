package com.intelsvn.taskmanagement.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.intelsvn.taskmanagement.R;
import com.intelsvn.taskmanagement.presenter.implement.ResetPasswordImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.ResetPasswordInterface;
import com.intelsvn.taskmanagement.view.interfaces.ResetPasswordView;

public class ResetPassword extends AppCompatActivity implements ResetPasswordView {

    Toolbar toolbar;
    TextInputEditText edtUsername, edtEmail, edtPhone;
    Button btnResetPassword;

    ResetPasswordInterface resetPasswordInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reset Password");

        initViews();
        initInterfaces();
        addEvents();
    }

    private void initInterfaces() {
        resetPasswordInterface = new ResetPasswordImplementation(this);
    }

    private void addEvents() {
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                if (validateResetPassword(username, email, phone)) {
                    resetPasswordInterface.resetPassword(username, email, phone);
                }
            }
        });
    }

    private boolean validateResetPassword(String username, String email, String phone) {
        if (username.isEmpty()) {
            setErrorEditTextUsername("Username can't be null");
            return false;
        }

        if (email.isEmpty()) {
            setErrorEditTextEmail("Email can't be null");
            return false;
        }

        if (phone.isEmpty()) {
            setErrorEditTextPhone("Phone can't be null");
            return false;
        }
        return true;
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        btnResetPassword = findViewById(R.id.btnResetPassword);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void setErrorEditTextUsername(String message) {
        edtUsername.setError(message);
        edtUsername.requestFocus();
    }

    @Override
    public void setErrorEditTextEmail(String message) {
        edtEmail.setError(message);
        edtEmail.requestFocus();
    }

    @Override
    public void setErrorEditTextPhone(String message) {
        edtPhone.setError(message);
        edtPhone.requestFocus();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }
}
