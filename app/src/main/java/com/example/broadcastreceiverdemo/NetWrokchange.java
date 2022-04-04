package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/*
 * 静态注册
 */
public class NetWrokchange extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
        //判断网络连接状态和连接类型
        if(isConnected ){
            if(isWiFi) {
                Log.d("netWrork", "network is avilable that type is Wifi ");
            }else {
                Log.d("netWrork", "network is avilable that type is not Wifi ");
            }
        } else{
            Log.d("netWrork","network is unavilable");
        }

    }
}
