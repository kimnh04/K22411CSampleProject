package com.nguyenkim.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenkim.k22411csampleproject.models.Customer;
import com.nguyenkim.k22411csampleproject.models.ListCustomer;
import com.nguyenkim.k22411csampleproject.models.ListPaymentMethod;
import com.nguyenkim.k22411csampleproject.models.PaymentMethod;

public class PaymentMethodConnector {
    ListPaymentMethod listPaymentMethod;

    public ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database) {
        listPaymentMethod = new ListPaymentMethod();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod pm = new PaymentMethod();
            pm.setId(id);
            pm.setName(name);
            pm.setDescription(description);
            listPaymentMethod.addPaymentMethod(pm); // thêm vào danh sách phương thức thanh toán
        }

        cursor.close();
        return listPaymentMethod;
    }
}
