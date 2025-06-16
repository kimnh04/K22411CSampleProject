package com.nguyenkim.k22411csampleproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.connectors.CustomerConnector;
import com.nguyenkim.connectors.SQLiteConnector;
import com.nguyenkim.k22411csampleproject.models.Customer;
import com.nguyenkim.k22411csampleproject.models.ListCustomer;

public class CustomerManagementActivity extends AppCompatActivity {

    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    CustomerConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        addEvents();
    }

    private void addViews() {
        lvCustomer = findViewById(R.id.lvCustomer);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        connector = new CustomerConnector();

        SQLiteConnector dbConnector = new SQLiteConnector(this);
        SQLiteDatabase db = dbConnector.openDatabase();

        ListCustomer lc = connector.getAllCustomers(db);
        adapter.addAll(lc.getCustomers());
        lvCustomer.setAdapter(adapter);
    }

    private void addEvents() {
        lvCustomer.setOnItemLongClickListener((parent, view, position, id) -> {
            Customer c = adapter.getItem(position);
            displayCustomerDetailActivity(c);
            return true;
        });
    }

    private void displayCustomerDetailActivity(Customer c) {
        Intent intent = new Intent(this, CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER", c);
        startActivityForResult(intent, 400); // 400: sửa dữ liệu
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_new_customer) {
            Intent intent = new Intent(this, CustomerDetailActivity.class);
            startActivityForResult(intent, 300); // 300: thêm mới
            return true;
        } else if (item.getItemId() == R.id.menu_broadcast_advertising) {
            Toast.makeText(this, "Gửi quảng cáo hàng loạt tới khách hàng", Toast.LENGTH_LONG).show();
            return true;
        } else if (item.getItemId() == R.id.menu_help) {
            Toast.makeText(this, "Mở website hướng dẫn sử dụng", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        if (requestCode == 300 && resultCode == 500) { // thêm mới
            Customer c = (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            if (c != null) {
                processSaveNewCustomer(c);
            } else {
                Log.e("CustomerManagement", "NEW_CUSTOMER is null when adding");
            }
        } else if (requestCode == 400 && resultCode == 500) { // cập nhật
            Customer c = (Customer) data.getSerializableExtra("NEW_CUSTOMER");
            if (c != null) {
                processUpdateCustomer(c);
            } else {
                Log.e("CustomerManagement", "NEW_CUSTOMER is null when updating");
            }
        } else if (requestCode == 400 && resultCode == 600) { // xóa
            String id = data.getStringExtra("CUSTOMER_TO_REMOVE");
            if (id != null) {
                processRemoveCustomer(id);
            } else {
                Log.e("CustomerManagement", "CUSTOMER_TO_REMOVE is null when deleting");
            }
        }
    }

    private void processSaveNewCustomer(Customer c) {
        SQLiteDatabase db = new SQLiteConnector(this).openDatabase();
        CustomerConnector cc = new CustomerConnector();
        long flag = cc.saveNewCustomer(c, db);
        if (flag > 0) {
            refreshCustomerList(db, cc);
        } else {
            Toast.makeText(this, "Thêm khách hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void processUpdateCustomer(Customer c) {
        SQLiteDatabase db = new SQLiteConnector(this).openDatabase();
        CustomerConnector cc = new CustomerConnector();
        long flag = cc.saveUpdateCustomer(c, db);
        if (flag > 0) {
            refreshCustomerList(db, cc);
        } else {
            Toast.makeText(this, "Cập nhật khách hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void processRemoveCustomer(String id) {
        SQLiteDatabase db = new SQLiteConnector(this).openDatabase();
        CustomerConnector cc = new CustomerConnector();
        long flag = cc.removeCustomer(Integer.parseInt(id), db); // ép kiểu String -> int
        if (flag > 0) {
            refreshCustomerList(db, cc);
        } else {
            Toast.makeText(this, "Xóa khách hàng thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshCustomerList(SQLiteDatabase db, CustomerConnector cc) {
        adapter.clear();
        adapter.addAll(cc.getAllCustomers(db).getCustomers());
    }
}