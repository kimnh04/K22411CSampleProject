package com.nguyenkim.k22411csampleproject.models;

public class Orders implements java.io.Serializable {
    private int id;
    private String Code;
    private String Name;
    private int Employeeid;
    private int Customerid;
    private String OrderDate;
    private String Description;
    private String Status;
    private String StatusDescription;

    public Orders() {
    }

    public Orders(int id, String code, String name, int employeeid, int customerid, String orderDate, String description, String status, String statusDescription) {
        this.id = id;
        Code = code;
        Name = name;
        Employeeid = employeeid;
        Customerid = customerid;
        OrderDate = orderDate;
        Description = description;
        Status = status;
        StatusDescription = statusDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getEmployeeid() {
        return Employeeid;
    }

    public void setEmployeeid(int employeeid) {
        Employeeid = employeeid;
    }

    public int getCustomerid() {
        return Customerid;
    }

    public void setCustomerid(int customerid) {
        Customerid = customerid;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStatusDescription() {
        return StatusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        StatusDescription = statusDescription;
    }
}
