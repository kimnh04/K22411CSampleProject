package com.nguyenkim.k22411csampleproject.models;

import androidx.annotation.NonNull;

public class TelephonyInfor {
    private String name;
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TelephonyInfor() {
    }

    public TelephonyInfor(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    @NonNull
    @Override
    public String toString() {
        return this.name + " - " + this.phone;
    }

}
