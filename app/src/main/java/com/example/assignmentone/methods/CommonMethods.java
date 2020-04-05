package com.example.assignmentone.methods;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import com.example.assignmentone.Connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommonMethods {

    public static String noNetwork = "Check your Internet Connection!";
    public static String noInternetAccess = "No Internet Access!";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    public static boolean isConnectedToInternet() throws InterruptedException, IOException {
        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
    }


    /*public static boolean isInternetAvailable() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }*/


    public static void checkConnection(Context context) {
        if (!isNetworkAvailable(context)) {
            gotoConnection(context, CommonMethods.noNetwork);
        }
        /*else {
            try {
                if (!isConnectedToInternet()) {
                    gotoConnection(context, CommonMethods.noInternetAccess);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }

    private static void gotoConnection(Context context, String msg) {
        Intent intent = new Intent(context, Connection.class);
        intent.putExtra("msg", msg);
        context.startActivity(intent);


    }

    /*private static BroadcastReceiver networkChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("app","Network connectivity change");
        }
    };*/

   /* public static BroadcastReceiver networkChangeReceiver(final Runnable callback) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                callback.run();
            }
        };
        return broadcastReceiver;
    }*/


}
