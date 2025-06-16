package com.nguyenkim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenkim.k22411csampleproject.R;
import com.nguyenkim.k22411csampleproject.models.OrdersViewer;

public class OrdersViewerAdapter extends ArrayAdapter<OrdersViewer> {
    Context context;
    int resource;
    public OrdersViewerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(resource, parent, false);

        TextView txtOrderCode = item.findViewById(R.id.txtOrderCode);
        TextView txtOrderDate = item.findViewById(R.id.txtOrderDate);
        TextView txtCustomerName = item.findViewById(R.id.txtCustomerName);
        TextView txtTotalOrderValue = item.findViewById(R.id.txtTotalOrderValue);
        // Lấy đối tượng OrdersViewer tại vị trí hiện tại

        OrdersViewer ov = getItem(position);
        txtOrderCode.setText(ov.getCode());
        txtOrderDate.setText(ov.getOrderDate());
        txtCustomerName.setText(ov.getCustomerName());
        txtTotalOrderValue.setText(String.valueOf(ov.getTotalOrderValue() +"VNĐ"));

        return item;
    }
}
