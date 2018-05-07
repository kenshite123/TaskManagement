package com.intelsvn.taskmanagement.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.intelsvn.taskmanagement.R;
import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.presenter.implement.LoginImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.LoginInterface;
import com.intelsvn.taskmanagement.view.interfaces.LoginView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity implements LoginView {

    TextInputEditText edtUsername;
    TextInputEditText edtPassword;
    Button btnLogin;
    TextView txtForgotPassword;

    LoginInterface loginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        processCopy();

        initViews();

        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtForgotPassword.setPaintFlags(txtForgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        loginInterface = new LoginImplementation(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (validateLogin(username, password)) {
                    loginInterface.login(username, password);
                }
            }
        });

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPassword.class));
            }
        });
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private boolean validateLogin(String username, String password) {
        if (username.isEmpty()) {
            setErrorEditTextUsername("Username can't be null");
            return false;
        }

        if (password.isEmpty()) {
            setErrorEditTextPassword("Password can't be null");
            return false;
        }
        return true;
    }

    @Override
    public void setErrorEditTextUsername(String message) {
        edtUsername.setError(message);
        edtUsername.requestFocus();
    }

    @Override
    public void setErrorEditTextPassword(String message) {
        edtPassword.setError(message);
        edtPassword.requestFocus();
    }

    @Override
    public void onLoginSuccess() {
        showToastMessage("Login Success");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TYPE", "All");
        startActivity(intent);
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

    private void processCopy() {
        //private app
        File dbFile = getDatabasePath(Constant.DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                try {
                    InputStream myInput;

                    myInput = getAssets().open(Constant.DATABASE_NAME);


                    // Path to the just created empty db
                    String outFileName = getApplicationInfo().dataDir + Constant.DB_PATH_SUFFIX + Constant.DATABASE_NAME;

                    // if the path doesn't exist first, create it
                    File f = new File(getApplicationInfo().dataDir + Constant.DB_PATH_SUFFIX);
                    if (!f.exists())
                        f.mkdir();

                    // Open the empty db as the output stream
                    OutputStream myOutput = new FileOutputStream(outFileName);

                    // transfer bytes from the inputfile to the outputfile
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = myInput.read(buffer)) > 0) {
                        myOutput.write(buffer, 0, length);
                    }

                    // Close the streams
                    myOutput.flush();
                    myOutput.close();
                    myInput.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Toast.makeText(this, "Copying sucess from Assets folder", Toast.LENGTH_LONG).show();
            }
            catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
