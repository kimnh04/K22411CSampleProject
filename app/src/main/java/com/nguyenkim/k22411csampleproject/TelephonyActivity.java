package com.nguyenkim.k22411csampleproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.adapters.TelephonyInforAdapter;
import com.nguyenkim.k22411csampleproject.models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvTelephonyinfor;
    TelephonyInforAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        getAllContacts();
        addEvents();
    }

    private void addEvents() {
        lvTelephonyinfor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephonyInfor ti = adapter.getItem(position);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Request the CALL_PHONE permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        } else {
            // Permission already granted, make the call
            Uri uri = Uri.parse("tel:" + ti.getPhone());
            Intent intent = new Intent(Intent.ACTION_CALL, uri);
            startActivity(intent);
        }
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor == null) return;

            adapter.clear();
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name = cursor.getString(nameIndex);  // Get contact name
                String phone = cursor.getString(phoneIndex); // Get phone number

                TelephonyInfor ti = new TelephonyInfor();
                ti.setName(name);
                ti.setPhone(phone);
                adapter.add(ti); // Add to adapter
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Ensure cursor is closed
            }
        }
    }

    private void addViews() {
        lvTelephonyinfor = findViewById(R.id.lvTelephonyinfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephonyinfor.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                TelephonyInfor ti = adapter.getItem(lvTelephonyinfor.getCheckedItemPosition());
                if (ti != null) {
                    makeAPhoneCall(ti);
                }
            } else {
                // Permission denied
            }
        }
    }

    public void directCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }
    public void dialupCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
    public void sendSms(TelephonyInfor ti) {
//        Uri uri = Uri.parse("smsto:" + ti.getPhone());
//        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
//        intent.putExtra("sms_body", "Hello, this is a test message!");
//        startActivity(intent);
    }

}