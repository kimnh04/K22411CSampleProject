package com.nguyenkim.k22411csampleproject;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.connectors.EmployeeConnector;
import com.nguyenkim.connectors.SQLiteConnector;
import com.nguyenkim.k22411csampleproject.models.Employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName;
    EditText edtPassword;
    CheckBox chkSaveLoginInfor;
    Button btnLogin;
    TextView txtNetworkType;
    String DATABASE_NAME = "SalesDatabase.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";

    SQLiteDatabase database = null;
    BroadcastReceiver networkReceiver;

    private boolean doubleBackToExitPressedOnce = false;
    private static final long DOUBLE_BACK_PRESS_THRESHOLD = 1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        addView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        processCopy();
        setupBroadcastReceiver();
    }

    private void setupBroadcastReceiver() {
        networkReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                        txtNetworkType.setBackgroundColor(0xFF00FF00); // Màu xanh lá
                        txtNetworkType.setText("Connected to Wi-Fi");
                    } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        txtNetworkType.setBackgroundColor(0xFFFFFF00); // Màu vàng
                        txtNetworkType.setText("Connected to Mobile Data");
                    }
                } else {
                    txtNetworkType.setBackgroundColor(0xFFFF0000); // Màu đỏ
                    txtNetworkType.setText("No Network Connection");
                }
            }
        };
    }

    private void addView() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        chkSaveLoginInfor = findViewById(R.id.chkSaveLoginInfor);
        btnLogin = findViewById(R.id.btnLogin);
        txtNetworkType = findViewById(R.id.txtNetworkType);
    }

    public void do_login(View view) {
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        EmployeeConnector ec = new EmployeeConnector();
        Employee emp = ec.login(new SQLiteConnector(this).openDatabase(), usr, pwd);
        if (emp != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login failed - please check your account login!", Toast.LENGTH_LONG).show();
        }
    }

    public void do_exit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        Resources res = getResources();
        builder.setTitle(res.getText(R.string.confirm_exit_title));
        builder.setMessage(res.getText(R.string.confirm_exit_message));
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton(res.getText(R.string.confirm_exit_yes), (dialog, which) -> finish());
        builder.setNegativeButton(res.getText(R.string.confirm_exit_no), (dialog, which) -> dialog.cancel());
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (doubleBackToExitPressedOnce) {
                finish();
                return true;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, DOUBLE_BACK_PRESS_THRESHOLD);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void saveLoginInformation() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String usr = edtUserName.getText().toString();
        String pwd = edtPassword.getText().toString();
        boolean isSave = chkSaveLoginInfor.isChecked();
        editor.putString("USERNAME", usr);
        editor.putString("PASSWORD", pwd);
        editor.putBoolean("SAVED", isSave);
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveLoginInformation();
        if (networkReceiver != null) {
            unregisterReceiver(networkReceiver);
        }
    }

    public void retoreLoginInformation() {
        SharedPreferences preferences = getSharedPreferences("LOGIN_INFORMATION", MODE_PRIVATE);
        String usr = preferences.getString("USERNAME", "");
        String pwd = preferences.getString("PASSWORD", "");
        boolean isSave = preferences.getBoolean("SAVED", true);
        edtUserName.setText(usr);
        edtPassword.setText(pwd);
        chkSaveLoginInfor.setChecked(isSave);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retoreLoginInformation();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkReceiver, filter);
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}