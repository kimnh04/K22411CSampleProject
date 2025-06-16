//package com.nguyenkim.k22411csampleproject;
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.PendingIntent;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.telephony.SmsManager;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.nguyenkim.k22411csampleproject.models.TelephonyInfor;
//
//
//public class SendSMSActivity extends AppCompatActivity {
//    TextView txtTelephonyName;
//    TextView txtTelephonyNumber;
//    EditText edtBody;
//    ImageView imgSendSms1;
//    ImageView imgSendSms2;
//    TelephonyInfor ti;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_send_smsactivity);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        addViews();
//
//        imgSendSms1.setOnClickListener(v -> {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
//            } else {
//                sendSms(ti, edtBody.getText().toString());
//            }
//        });
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                sendSms(ti, edtBody.getText().toString());
//            } else {
//                Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void addEvents() {
//        imgSendSms1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // ko quan tâm gửi sms thành công hay ko
//                sendSms(ti, edtBody.getText().toString());
//            }
//        });
//        imgSendSms2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // quan tâm gửi sms thành công hay ko
//                sendSmsPendingIntent(ti, edtBody.getText().toString());
//            }
//        });
//    }
//
//    private void addViews() {
//        txtTelephonyName = findViewById(R.id.txtTelephonyName);
//        txtTelephonyNumber = findViewById(R.id.txtTelephonyNumber);
//        edtBody = findViewById(R.id.edtBody);
//        imgSendSms1 = findViewById(R.id.imgSendSms1);
//        imgSendSms2 = findViewById(R.id.imgSendSms2);
//
//        Intent intent = getIntent();
//        ti=(TelephonyInfor) intent.getSerializableExtra("TI");
//        if (ti != null) {
//            txtTelephonyName.setText(ti.getName());
//            txtTelephonyNumber.setText(ti.getPhone());
//        }
//
//
//    }
//
//    public  void sendSms(TelephonyInfor ti, String content)
//    {
//        final SmsManager sms = SmsManager.getDefault();
//
//        sms.sendTextMessage( ti.getPhone(), null, content, null, null);
//        Toast.makeText(SendSMSActivity.this, "Đã gửi tin nhắn tới "+ti.getPhone(),
//                Toast.LENGTH_LONG).show();
//    }
//    @SuppressLint("UnspecifiedRegisterReceiverFlag")
//    public  void sendSmsPendingIntent(TelephonyInfor ti, String content)
//    {
//        //lấy mặc định SmsManager
//        final SmsManager sms = SmsManager.getDefault();
//        Intent msgSent = new Intent("ACTION_MSG_SENT");
////Khai báo pendingintent để kiểm tra kết quả
//        final PendingIntent pendingMsgSent =
//                PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);
//        registerReceiver(new BroadcastReceiver() {
//            @SuppressLint("UnspecifiedRegisterReceiverFlag")
//            public void onReceive(Context context, Intent intent) {
//                int result = getResultCode();
//                String msg="Send OK";
//                if (result != Activity.RESULT_OK) {
//                    msg="Send failed";
//                }
//                Toast.makeText(SendSMSActivity.this, msg,
//                        Toast.LENGTH_LONG).show();
//            }
//        }, new IntentFilter("ACTION_MSG_SENT"));
////Gọi hàm gửi tin nhắn đi
//        sms.sendTextMessage(ti.getPhone(), null, content,
//                pendingMsgSent, null);
//    }
//}
package com.nguyenkim.k22411csampleproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.k22411csampleproject.models.TelephonyInfor;

public class SendSMSActivity extends AppCompatActivity {
    private TextView txtTelephonyName, txtTelephonyNumber;
    private EditText edtBody;
    private ImageView imgSendSms1, imgSendSms2;
    private TelephonyInfor ti;
    private BroadcastReceiver smsStatusReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_smsactivity);

        // Handle edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        txtTelephonyName = findViewById(R.id.txtTelephonyName);
        txtTelephonyNumber = findViewById(R.id.txtTelephonyNumber);
        edtBody = findViewById(R.id.edtBody);
        imgSendSms1 = findViewById(R.id.imgSendSms1);
        imgSendSms2 = findViewById(R.id.imgSendSms2);

        // Retrieve TelephonyInfor object from intent
        Intent intent = getIntent();
        ti = (TelephonyInfor) intent.getSerializableExtra("TI");
        if (ti != null) {
            txtTelephonyName.setText(ti.getName());
            txtTelephonyNumber.setText(ti.getPhone());
        }
    }

    private void addEvents() {
        imgSendSms1.setOnClickListener(v -> checkAndSendSms(false));
        imgSendSms2.setOnClickListener(v -> checkAndSendSms(true));
    }

    private void checkAndSendSms(boolean withPendingIntent) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        } else {
            if (withPendingIntent) {
                sendSmsPendingIntent(ti, edtBody.getText().toString());
            } else {
                sendSms(ti, edtBody.getText().toString());
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSms(ti, edtBody.getText().toString());
        } else {
            Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSms(TelephonyInfor ti, String content) {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(ti.getPhone(), null, content, null, null);
        Toast.makeText(this, "Message sent to " + ti.getPhone(), Toast.LENGTH_LONG).show();
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void sendSmsPendingIntent(TelephonyInfor ti, String content) {
        SmsManager sms = SmsManager.getDefault();
        Intent msgSent = new Intent("ACTION_MSG_SENT");
        PendingIntent pendingMsgSent = PendingIntent.getBroadcast(this, 0, msgSent, PendingIntent.FLAG_IMMUTABLE);

        // Register BroadcastReceiver to handle SMS status
        smsStatusReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = getResultCode();
                String msg = (result == Activity.RESULT_OK) ? "Send OK" : "Send failed";
                Toast.makeText(SendSMSActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(smsStatusReceiver, new IntentFilter("ACTION_MSG_SENT"), Context.RECEIVER_NOT_EXPORTED);

        // Send SMS with PendingIntent
        sms.sendTextMessage(ti.getPhone(), null, content, pendingMsgSent, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister BroadcastReceiver to prevent memory leaks
        if (smsStatusReceiver != null) {
            unregisterReceiver(smsStatusReceiver);
        }
    }
}