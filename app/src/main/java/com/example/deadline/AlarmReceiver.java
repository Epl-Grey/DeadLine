package com.example.deadline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM WORKS!!!", Toast.LENGTH_SHORT).show();
        NotificationMaker.makeNotify(context.getApplicationContext(), "Уведомление", "Попить липтон", "В 18:30");
    }
}