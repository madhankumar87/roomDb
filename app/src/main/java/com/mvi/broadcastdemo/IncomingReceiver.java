package com.mvi.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.TelephonyManager;

public class IncomingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String number = intent.getExtras().getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
            dbHelper.saveIncomingNumber(number, sqLiteDatabase);
            dbHelper.close();
        }

        Intent updateIntent = new Intent(DbContact.UPDATE_UI_FILTER);
        context.sendBroadcast(updateIntent);

    }
}
