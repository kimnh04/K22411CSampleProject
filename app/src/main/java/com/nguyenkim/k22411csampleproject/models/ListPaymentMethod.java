package com.nguyenkim.k22411csampleproject.models;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod> paymentMethods;
    public ListPaymentMethod() {
        paymentMethods = new ArrayList<>();
    }
//constructor with parameters
    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }


    public void addPaymentMethod(PaymentMethod pm) {
        paymentMethods.add(pm);
    }
    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void gen_payment_method() {
        paymentMethods.add(new PaymentMethod(1, "Banking Account", "Chuyển khoản ngân hàng"));
        paymentMethods.add(new PaymentMethod(2, "Momo", "Thanh toán ví MOMO"));
        paymentMethods.add(new PaymentMethod(3, "Cash", "Thanh toán tiền mặt"));
        paymentMethods.add(new PaymentMethod(4, "COD", "Thanh toán khi nhận hàng"));
    }


}
