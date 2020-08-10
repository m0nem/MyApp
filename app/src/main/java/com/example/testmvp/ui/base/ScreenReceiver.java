package com.example.testmvp.ui.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){


        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){

            Log.e("Logon", "The screen is on.");

        }

        else{

            Log.e("Logoff", "The screen is off.");

        }

    }

}
