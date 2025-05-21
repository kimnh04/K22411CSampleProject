package com.nguyenkim.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenkim.k22411csampleproject.models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edt_product_id;
    EditText edt_product_name;
    EditText edt_product_quantity;
    EditText edt_product_price;
    EditText edt_product_image_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        edt_product_id=findViewById(R.id.edt_product_id);
        edt_product_name=findViewById(R.id.edt_product_name);
        edt_product_quantity=findViewById(R.id.edt_product_quantity);
        edt_product_price=findViewById(R.id.edt_product_price);
        edt_product_image_id=findViewById(R.id.edt_product_image_id);
        // Lấy dữ liệu từ Intent
        display_product_infor();
    }

    private void display_product_infor() {
        // Lấy intent từ bên ProductManagementActivity gửi qua;
         Intent intent = getIntent();
        // Lấy dữ liệu SELECTED_PRODUCT từ intent
         Product p = (Product) intent.getSerializableExtra("SELECTED_PRODUCT");
         if(p==null)
             return;
        // Hiển thị thông tin sản phẩm lên các EditText
        edt_product_id.setText(p.getId()+"");
        edt_product_name.setText(p.getName());
        edt_product_quantity.setText(p.getQuantity()+"");
        edt_product_price.setText(p.getPrice()+"");
        edt_product_image_id.setText(p.getImageId()+"");
    }
}