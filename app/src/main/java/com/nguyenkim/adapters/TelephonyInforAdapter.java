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
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resources, parent, false);
            holder = new ViewHolder();
            holder.txtTelephonyName = convertView.findViewById(R.id.txtTelephonyName);
            holder.txtTelephonyNumber = convertView.findViewById(R.id.txtTelephonyNumber);
            holder.imgDirectCall = convertView.findViewById(R.id.imgDirectCall);
            holder.imgDialUp = convertView.findViewById(R.id.imgDialUp);
            holder.imgSendSms = convertView.findViewById(R.id.imgSendSms);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TelephonyInfor ti = getItem(position);
        if (ti != null) {
            holder.txtTelephonyName.setText(ti.getName());
            holder.txtTelephonyNumber.setText(ti.getPhone());

            if (context instanceof TelephonyActivity) {
                TelephonyActivity telephonyActivity = (TelephonyActivity) context;

                holder.imgDirectCall.setOnClickListener(v -> telephonyActivity.directCall(ti));
                holder.imgDialUp.setOnClickListener(v -> telephonyActivity.dialupCall(ti));
            }

            holder.imgSendSms.setOnClickListener(v -> {
                Intent intent = new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI", ti);
                context.startActivity(intent);
            });
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txtTelephonyName;
        TextView txtTelephonyNumber;
        ImageView imgDirectCall;
        ImageView imgDialUp;
        ImageView imgSendSms;
    }
}