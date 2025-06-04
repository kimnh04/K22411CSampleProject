package com.nguyenkim.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.nguyenkim.k22411csampleproject.R;
import com.nguyenkim.k22411csampleproject.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;

    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        // nhân bản giao diện theo thứ tự
        View item = inflater.inflate(this.resource, null);
        // lúc này: Toàn bộ View

        ImageView imgProduct = item.findViewById(R.id.imgProduct);
        TextView txtProductId = item.findViewById(R.id.txtProductId);
        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity = item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice = item.findViewById(R.id.txtProductPrice);
        ImageView imgCart = item.findViewById(R.id.imgCart);

        // lấy mô hình
        Product p = getItem(position);
        // Rải dữ liệu của Product lên giao diện trong item
//        if (p != null) {
//            imgProduct.setImageResource(p.getImageId());
//            txtProductId.setText(String.valueOf(p.getId()));
//            txtProductName.setText(p.getName());
//            txtProductQuantity.setText(String.valueOf(p.getQuantity()));
//            txtProductPrice.setText(String.valueOf(p.getPrice()));
//        }
        imgProduct.setImageResource(p.getImageId());
        txtProductId.setText(p.getId()+"");
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(p.getQuantity()+"");
        txtProductPrice.setText(p.getPrice()+"(VNĐ)");


        return item;
    }
}
