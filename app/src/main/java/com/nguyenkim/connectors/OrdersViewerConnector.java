package com.nguyenkim.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenkim.k22411csampleproject.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer> getAllOrdersViewer(SQLiteDatabase database) {
        if (database == null) {
            throw new IllegalArgumentException("Database cannot be null.");
        }

        ArrayList<OrdersViewer> ordersList = new ArrayList<>();
        String sql = "SELECT " +
                "o.Id AS OrderId, " +
                "o.Code AS OrderCode, " +
                "o.OrderDate, " +
                "e.Name AS EmployeeName, " +
                "c.Name AS CustomerName, " +
                "SUM((od.Quantity * od.Price - od.Discount / 100.0 * od.Quantity * od.Price) * (1 + od.VAT / 100.0)) AS TotalOrderValue " +
                "FROM Orders o " +
                "JOIN Employee e ON o.EmployeeId = e.Id " +
                "JOIN Customer c ON o.CustomerId = c.Id " +
                "JOIN OrderDetails od ON o.Id = od.OrderId " +
                "GROUP BY o.Id, o.Code, o.OrderDate, e.Name, c.Name;";

        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, null);
            while (cursor.moveToNext()) {
                OrdersViewer order = new OrdersViewer();
                order.setId(cursor.getInt(0));
                order.setCode(cursor.getString(1));
                order.setOrderDate(cursor.getString(2));
                order.setEmployeeName(cursor.getString(3));
                order.setCustomerName(cursor.getString(4));
                order.setTotalOrderValue(cursor.getDouble(5));
                ordersList.add(order);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return ordersList;
    }
}