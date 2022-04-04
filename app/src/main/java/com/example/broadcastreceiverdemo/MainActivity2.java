package com.example.broadcastreceiverdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

/*
 * 2.Broadcast Receiver判断并监测网络连接状态
 */

public class MainActivity2 extends AppCompatActivity {


    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

        //调用动态注册的广播接收器
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
        //取消注册
        unregisterReceiver(networkChangeReceiver);
    }

    /*
     * 动态注册
     */

    class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            /*boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;*/
            //判断网络连接状态和连接类型
            if(isConnected ){
                Log.d("netWrork","network is avilable that type is Wifi ");
            }/*else if(isConnected && isWiFi) {
                Log.d("netWrork","network is avilable that type is not wifi");*/
            else{
                Log.d("netWrork","network is unavilable");
            }

        }
    }
}