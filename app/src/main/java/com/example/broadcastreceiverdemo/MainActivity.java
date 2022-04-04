package com.example.broadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PowerConnectionReceiver powerConnectionReceiver = new PowerConnectionReceiver();

        IntentFilter intentFilter = new IntentFilter();

        /*intentFilter.addAction(android.intent.action.ACTION_BATTERY_LOW);
        registerReceiver(powerConnectionReceiver,intentFilter);*/



        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = MainActivity.this.registerReceiver(null,ifilter);

       /* PowerConnectionReceiver powerConnectionReceiver = new PowerConnectionReceiver();
        powerConnectionReceiver.onReceive(MainActivity.this,batteryStatus);*/


        /*Log.d("abc", String.valueOf(powerConnectionReceiver.getSb()));*/

        //判断当前的充电状态
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        Log.d("status", String.valueOf(isCharging));

        //判断是usb接口还是充电器进行充电
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        Log.d("usb", String.valueOf(usbCharge));
        Log.d("ac", String.valueOf(acCharge));
    }
}