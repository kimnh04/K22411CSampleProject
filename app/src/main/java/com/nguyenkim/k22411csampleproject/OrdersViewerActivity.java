package com.nguyenkim.k22411csampleproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.adapters.OrdersViewerAdapter;
import com.nguyenkim.connectors.OrdersViewerConnector;
import com.nguyenkim.connectors.SQLiteConnector;
import com.nguyenkim.k22411csampleproject.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerActivity extends AppCompatActivity {
    ListView lvOrdersViewer;
    OrdersViewerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrdersViewer = findViewById(R.id.lvOrdersViewer);
        adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
        lvOrdersViewer.setAdapter(adapter);

        // Set item click listener using lambda for cleaner code
        lvOrdersViewer.setOnItemClickListener((parent, view, position, id) -> {
            OrdersViewer selectedOrder = adapter.getItem(position);
            showOrderDetails(selectedOrder);
        });

        // Initialize SQLiteConnector and open the database
        SQLiteConnector connector = new SQLiteConnector(this);
        SQLiteDatabase database = connector.openDatabase(); // Ensure the database is opened here

        // Fetch data using OrdersViewerConnector
        OrdersViewerConnector ovc = new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset = ovc.getAllOrdersViewer(database);

        // Add data to adapter
        for (OrdersViewer order : dataset) {
            adapter.add(order);
        }
        adapter.notifyDataSetChanged();
    }

    private void showOrderDetails(OrdersViewer order) {
        Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailsActivity.class);
        // We use Parcelable/Serializable here because we made Orders implement Serializable
        intent.putExtra("SELECTED_ORDER", order);
        startActivity(intent);
    }


}