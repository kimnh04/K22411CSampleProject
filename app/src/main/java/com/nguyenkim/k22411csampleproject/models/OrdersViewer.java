package com.nguyenkim.k22411csampleproject.models;

import java.io.Serializable;

public class OrdersViewer extends Orders implements Serializable {
    private String EmployeeName;
    private String CustomerName;
    private double TotalOrderValue;

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public double getTotalOrderValue() {
        return TotalOrderValue;
    }

    public void setTotalOrderValue(double totalOrderValue) {
        TotalOrderValue = totalOrderValue;
    }

}
