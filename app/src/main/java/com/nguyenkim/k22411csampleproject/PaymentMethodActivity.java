package com.nguyenkim.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.adapters.PaymentMethodAdapter;
import com.nguyenkim.connectors.PaymentMethodConnector;
import com.nguyenkim.connectors.SQLiteConnector;
import com.nguyenkim.k22411csampleproject.models.ListPaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter PaymentMethodAdapter;
    ListPaymentMethod lpm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();

    }

    private void addViews() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        PaymentMethodAdapter = new PaymentMethodAdapter(this, R.layout.item_payment_method);
        lvPaymentMethod.setAdapter(PaymentMethodAdapter);

        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        PaymentMethodConnector paymentMethodConnector = new PaymentMethodConnector();
        lpm = paymentMethodConnector.getAllPaymentMethods(sqLiteConnector.getDatabase());

        PaymentMethodAdapter.addAll(lpm.getPaymentMethods());
    }
}