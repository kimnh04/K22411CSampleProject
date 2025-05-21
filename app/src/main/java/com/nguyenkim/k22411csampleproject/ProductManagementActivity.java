package com.nguyenkim.k22411csampleproject;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.nguyenkim.connectors.CategoryConnector;
//import com.nguyenkim.connectors.ProductConnector;
import com.nguyenkim.k22411csampleproject.models.Category;
import com.nguyenkim.k22411csampleproject.models.ListCategory;
import com.nguyenkim.k22411csampleproject.models.Product;

public class ProductManagementActivity extends AppCompatActivity {
    Spinner spinnerCategory;
    ArrayAdapter<Category>adapterCategory;
    ListCategory listCategory;
    ListView lvProduct;
    ArrayAdapter<Product>adapterProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }


//    private void addEvents() {
//        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Category c=adapterCategory.getItem(position);
//                displayProductsByCategory(c);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });1
//
//    }
    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category c=adapterCategory.getItem(position);
                displayProductsByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p = adapterProduct.getItem(position);
                Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
                intent.putExtra("SELECTED_PRODUCT", p);
                startActivity(intent);
            }
        });
    }

    private void displayProductsByCategory(Category c) {
        // xóa dữ liệu cũ trong Listview đi:
        adapterProduct.clear();
        // nạp mới lại dữ liệu
        adapterProduct.addAll(c.getProducts());
    }

    private void addViews() {
        spinnerCategory=findViewById(R.id.spinnerCategory);
        adapterCategory=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
                lvProduct.setAdapter(adapterProduct);

    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_new_product)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "New Product", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.menu_product_broadcast_advertising)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Gửi quảng cáo về sản phẩm mới", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.menu_product_help)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Mở website hướng dẫn mua hàng",
                    Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}