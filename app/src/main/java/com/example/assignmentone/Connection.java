package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentone.methods.CommonMethods;

import java.io.IOException;

public class Connection extends AppCompatActivity {
    TextView connection_msg;
    AppCompatButton connection_retry;
    Context context;
    Activity activity;
    final String noNetwork = CommonMethods.noNetwork;
    final String noInternetAccess = CommonMethods.noInternetAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        context = this;
        activity = ((Activity) context);
        connection_msg = findViewById(R.id.connection_msg);
        connection_retry = findViewById(R.id.connection_retry);



        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");

        connection_msg.setText(msg);

        connection_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectionCheck();
            }
        });


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
               // activity.finish();
              //  context.startActivity(new Intent(context,MainActivity.class));
               // Toast.makeText(context, "connection Switch", Toast.LENGTH_LONG).show();

                connectionCheck();

            }
        }, intentFilter);

    }

    public void connectionCheck(){
        if (CommonMethods.isNetworkAvailable(context)) {
            /*try {
                if (CommonMethods.isConnectedToInternet()) {*/
                    context.startActivity(new Intent(context, MainActivity.class));
            /*    } else {
                    connection_msg.setText(noInternetAccess);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } else {
            connection_msg.setText(noNetwork);
        }
    }

}
