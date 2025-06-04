package com.nguyenkim.k22411csampleproject.models;

public class OrderDetails {
    private int ID;
    private int Orderid;
    private int Productid;
    private int Quantity;
    private double Price;
    private double Discount;
    private double VAT;
    private double TotalValue;
    public OrderDetails() {
    }

    public OrderDetails(int ID, int orderid, int productid, int quantity, double price, double discount, double VAT, double totalValue) {
        this.ID = ID;
        Orderid = orderid;
        Productid = productid;
        Quantity = quantity;
        Price = price;
        Discount = discount;
        this.VAT = VAT;
        TotalValue = totalValue;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOrderid() {
        return Orderid;
    }

    public void setOrderid(int orderid) {
        Orderid = orderid;
    }

    public int getProductid() {
        return Productid;
    }

    public void setProductid(int productid) {
        Productid = productid;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getTotalValue() {
        TotalValue = (Price * Quantity - Discount/100 * Quantity * Price) * (1 + VAT/100);
        return TotalValue;
    }


}
