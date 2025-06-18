package com.nguyenkim.k22411csampleproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.nguyenkim.adapters.TelephonyInforAdapter;
import com.nguyenkim.k22411csampleproject.models.TelephonyInfor;
import com.nguyenkim.utils.NetworkUtils;

import java.util.function.Predicate;

public class TelephonyActivity extends AppCompatActivity {
    private static final int REQUEST_READ_CONTACTS = 100;
    private static final int REQUEST_CALL_PHONE = 200;

    private ListView lvTelephonyinfor;
    private TelephonyInforAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);
        addViews();

        if (lacksPermission(Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            getAllContacts();
        }

        addEvents();
    }

    private void addViews() {
        lvTelephonyinfor = findViewById(R.id.lvTelephonyinfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephonyinfor.setAdapter(adapter);
    }

    private void addEvents() {
        lvTelephonyinfor.setOnItemClickListener((parent, view, position, id) -> {
            TelephonyInfor ti = adapter.getItem(position);
            if (ti != null) {
                dialupCall(ti); // default to dial-up when clicking
            }
        });
    }

    // 📞 Direct call (requires permission)
    public void directCall(TelephonyInfor ti) {
        if (lacksPermission(Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + ti.getPhone()));
            startActivity(intent);
        }
    }

    // ☎️ Dial-up call (opens dialer, doesn't require CALL_PHONE permission)
    public void dialupCall(TelephonyInfor ti) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ti.getPhone()));
        startActivity(intent);
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
            if (cursor == null) return;

            adapter.clear();
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if (nameIndex == -1 || phoneIndex == -1) return;

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String phone = cursor.getString(phoneIndex);
                adapter.add(new TelephonyInfor(name, phone));
            }
        }
    }

    private boolean lacksPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telephony, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_viettel) {
            filterContacts(NetworkUtils::isViettel);
            Toast.makeText(this, "Viettel selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_mobifone) {
            filterContacts(NetworkUtils::isMobifone);
            Toast.makeText(this, "Mobifone selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_other) {
            filterContacts(NetworkUtils::isOther);
            Toast.makeText(this, "Other selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterContacts(Predicate<String> filter) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
            if (cursor == null) return;

            adapter.clear();
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            if (nameIndex == -1 || phoneIndex == -1) return;

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String phone = cursor.getString(phoneIndex);
                if (filter.test(phone)) {
                    adapter.add(new TelephonyInfor(name, phone));
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllContacts();
            } else {
                Toast.makeText(this, "Permission denied to read contacts", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CALL_PHONE) {
            // Optional: handle re-requested permission for direct call
        }
    }
}