package com.nguyenkim.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenkim.k22411csampleproject.models.OrderDetail;

import java.util.ArrayList;

public class OrderDetailConnector {
    public ArrayList<OrderDetail> getOrderDetailsByOrderId(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        // Updated SQL query: Replaced `od.Money` with `od.TotalValue`
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT od.Id, od.OrderId, od.ProductId, p.Name AS ProductName, ");
        builder.append("od.Price, od.Quantity, od.VAT, od.Discount, od.TotalValue ");
        builder.append("FROM OrderDetails od ");
        builder.append("JOIN Product p ON od.ProductId = p.Id ");
        builder.append("WHERE od.OrderId = ? ");
        builder.append("ORDER BY od.Id");

        Cursor cursor = database.rawQuery(builder.toString(), new String[]{String.valueOf(orderId)});

        while (cursor.moveToNext()) {
            OrderDetail detail = new OrderDetail();
            detail.setId(cursor.getInt(0));
            detail.setOrderId(cursor.getInt(1));
            detail.setProductId(cursor.getInt(2));
            detail.setProductName(cursor.getString(3));
            detail.setPrice(cursor.getDouble(4));
            detail.setQuantity(cursor.getInt(5));
            detail.setVat(cursor.getDouble(6));
            detail.setDiscount(cursor.getDouble(7));
            detail.setTotal(cursor.getDouble(8)); // Set TotalValue
            orderDetails.add(detail);
        }

        cursor.close();
        return orderDetails;
    }
}