//package com.nguyenkim.adapters;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.nguyenkim.k22411csampleproject.R;
//import com.nguyenkim.k22411csampleproject.SendSMSActivity;
//import com.nguyenkim.k22411csampleproject.TelephonyActivity;
//import com.nguyenkim.k22411csampleproject.models.TelephonyInfor;
//
//public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {
//    Activity context;
//    int resources;
//    public TelephonyInforAdapter(@NonNull Activity context, int resources) {
//        super(context, resources);
//        this.context = context;
//        this.resources = resources;
//    }
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = this.context.getLayoutInflater();
//        View item = inflater.inflate(this.resources, null);
//        TextView txtTelephonyName=item.findViewById(R.id.txtTelephonyName);
//        TextView txtTelephonyNumber=item.findViewById(R.id.txtTelephonyNumber);
//        ImageView imgDirectCall= item.findViewById(R.id.imgDirectCall);
//        ImageView imgDialUp= item.findViewById(R.id.imgDialUp);
//        ImageView imgSendSms= item.findViewById(R.id.imgSendSms);
//
//        TelephonyInfor ti=getItem(position);
//        txtTelephonyName.setText(ti.getName());
//        txtTelephonyNumber.setText(ti.getPhone());
//        // các sự kiện making telephony làm sau
//        imgDirectCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((TelephonyActivity) context).directCall(ti);
//            }
//        });
//        imgDialUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((TelephonyActivity) context).dialupCall(ti);
//            }
//        });
//        // set onclick để nhấn vô sms chuyển qua layout SendSMSActivity
//        imgSendSms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, SendSMSActivity.class);
//                intent.putExtra("TI", ti);
//                context.startActivity(intent);
//            }
//        });
//
//        // PHẢI LÀ RETURN ITEM
//        return item;
//
//    }
//
//
//}
package com.nguyenkim.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.nguyenkim.k22411csampleproject.R;
import com.nguyenkim.k22411csampleproject.SendSMSActivity;
import com.nguyenkim.k22411csampleproject.TelephonyActivity;
import com.nguyenkim.k22411csampleproject.models.TelephonyInfor;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {
    private final Activity context;
    private final int resources;

    public TelephonyInforAdapter(@NonNull Activity context, int resources) {
        super(context, resources);
        this.context = context;
        this.resources = resources;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            item = inflater.inflate(resources, parent, false);
        }

        TextView txtTelephonyName = item.findViewById(R.id.txtTelephonyName);
        TextView txtTelephonyNumber = item.findViewById(R.id.txtTelephonyNumber);
        ImageView imgDirectCall = item.findViewById(R.id.imgDirectCall);
        ImageView imgDialUp = item.findViewById(R.id.imgDialUp);
        ImageView imgSendSms = item.findViewById(R.id.imgSendSms);

        TelephonyInfor ti = getItem(position);
        if (ti != null) {
            txtTelephonyName.setText(ti.getName());
            txtTelephonyNumber.setText(ti.getPhone());

            imgDirectCall.setOnClickListener(v -> ((TelephonyActivity) context).directCall(ti));
            imgDialUp.setOnClickListener(v -> ((TelephonyActivity) context).dialupCall(ti));
            imgSendSms.setOnClickListener(v -> {
                Intent intent = new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI", ti);
                context.startActivity(intent);
            });
        }

        return item;
    }
}
