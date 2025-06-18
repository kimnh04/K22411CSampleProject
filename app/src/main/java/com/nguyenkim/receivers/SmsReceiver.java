package com.nguyenkim.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the received SMS message here
        // You can extract the SMS data from the intent and process it as needed
        Bundle bundle = intent.getExtras();
        Object[] appMessages = (Object[]) bundle.get("pdus");

        String phone, time, content;
        Date date;
        byte[] bytes;
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for(int i = 0; i < appMessages.length; i++)
        {
            bytes = (byte[]) appMessages[i];
            SmsMessage message = SmsMessage.createFromPdu(bytes);
            phone = message.getDisplayOriginatingAddress();
            date = new Date(message.getTimestampMillis());
            content = message.getDisplayMessageBody();
            time= sdf.format(date);
            String infor = phone+"\n"
                    + time + "\n"
                    + content;
            // ta sẽ gửi lên Activity để hiển thị sau
            Toast.makeText(context, infor, Toast.LENGTH_LONG).show();
        }

        }

    }

