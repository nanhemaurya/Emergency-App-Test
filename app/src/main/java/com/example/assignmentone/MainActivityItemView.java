package com.example.assignmentone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmentone.adapters.MainActivityContentViewAdapter;
import com.example.assignmentone.methods.ToolbarMethods;
import com.example.assignmentone.modals.MainActivityContentViewList;
import com.example.assignmentone.modals.emergency.Address;
import com.example.assignmentone.modals.emergency.Contacts;
import com.example.assignmentone.modals.emergency.Emergency;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivityItemView extends AppCompatActivity {

    Context context;
    Activity activity;
    ToolbarMethods toolbarMethods;
    MainActivityContentViewAdapter adapter;
    List<MainActivityContentViewList> mainActivityContentViewLists = new ArrayList<>();
    RelativeLayout popup_cont;
    AppCompatButton close_popup;
    TextView primary_number, secondary_number;
    LinearLayout number_cont;
    View popup_cont_bg;

    String mNumber;
    int numContHeight;

    public static int REQUEST_PERMISSION = 0;

    List<Contacts> contacts;
    String address_display_label = "", latitude = "", longitude = "", distance_km;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_item_view);
        context = this;
        activity = ((Activity) context);


        Intent intent = activity.getIntent();
        String title = intent.getStringExtra("title");
        String id = intent.getStringExtra("id");
        String value = intent.getStringExtra("value");

        toolbarMethods = new ToolbarMethods(this);
        toolbarMethods.allowBackButton(true);
        toolbarMethods.setTitle(title);


        /*===== Initialisations =====*/
        popup_cont = findViewById(R.id.popup_cont);
        close_popup = findViewById(R.id.close_popup);
        primary_number = findViewById(R.id.primary_number);
        secondary_number = findViewById(R.id.secondary_number);
        number_cont = findViewById(R.id.number_cont);
        popup_cont_bg = findViewById(R.id.popup_cont_bg);
        popup_cont.setVisibility(View.GONE);

        List<Emergency> emergency = new Gson().fromJson(value, new TypeToken<List<Emergency>>() {
        }.getType());

        assert emergency != null;
        for (Emergency emer : emergency) {
            if (emer.getContact_type().toString().equalsIgnoreCase(id)) {

                int emerId = emer.getId();
                String name = emer.getName();

                contacts = emer.getContacts();

                distance_km = "" + emer.getDistance_km();


                Address address = emer.getAddress();
                if (address != null) {
                    address_display_label = address.getDisplay_label();
                    latitude = address.getLatitude();
                    longitude = address.getLongitude();
                }


                mainActivityContentViewLists.add(
                        new MainActivityContentViewList(
                                emerId,
                                name,
                                address_display_label,
                                latitude,
                                longitude,
                                distance_km,
                                contacts));

            }
        }

       /* mainActivityContentViewLists.add(
                new MainActivityContentViewList(
                        1,
                        "ghkhjjjghj",
                        "gjhgjh",
                        "6787",
                        "68878",
                        "78687",
                        "[]"));*/

        RecyclerView contents_recycler_view = findViewById(R.id.contents_recycler_view);
        contents_recycler_view.setLayoutManager(new LinearLayoutManager(context));
        //main_content_recycler_view.setLayoutManager(new GridLayoutManager(context, 2));
        contents_recycler_view.setHasFixedSize(true);

        adapter = new MainActivityContentViewAdapter(context, mainActivityContentViewLists);
        contents_recycler_view.setAdapter(adapter);


        adapter.setOnItemClickListener(new MainActivityContentViewAdapter.OnItemClickListener() {
            @Override
            public void onContactItemClick(View v) {
                //Toast.makeText(context, v.getTag(R.string.contact_pNumber).toString(), Toast.LENGTH_LONG).show();
                String pNum, sNum;
                pNum = v.getTag(R.string.contact_pNumber).toString();
                sNum = v.getTag(R.string.contact_sNumber).toString();

                primary_number.setTag(R.string.contact_pNumber, pNum);
                secondary_number.setTag(R.string.contact_sNumber, sNum);

                openContactPopup(pNum, sNum);

            }
        });


        close_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeContactPopup();
            }
        });
        popup_cont_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {closeContactPopup();}
        });

        primary_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber = v.getTag(R.string.contact_pNumber).toString();
                makeCall();
            }
        });

        secondary_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNumber = v.getTag(R.string.contact_sNumber).toString();
                makeCall();
            }
        });



    }


    public void openContactPopup(String primaryNumber, String secondaryNumber) {

        popup_cont.setVisibility(View.VISIBLE);
        popup_cont_bg.setAlpha(0f);
        popup_cont.setAlpha(0f);





        if (primaryNumber.isEmpty()) {
            primary_number.setVisibility(View.GONE);
        } else {
            primary_number.setText(primaryNumber);
        }

        if (secondaryNumber.isEmpty()) {
            secondary_number.setVisibility(View.GONE);
        } else {
            secondary_number.setText(secondaryNumber);
        }

        final Handler openPopupCont = new Handler();
        final Handler popup = new Handler();
        popup.postDelayed(new Runnable() {
            @Override
            public void run() {
                numContHeight = number_cont.getMeasuredHeight();
                number_cont.setTranslationY(number_cont.getMeasuredHeight());
                popup_cont_bg.animate().alpha(1f).setDuration(200);
                openPopupCont.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        popup_cont.setAlpha(1f);
                        number_cont.animate().translationY(0).setDuration(250);
                    }
                }, 300);
            }
        }, 0);


    }

    public void closeContactPopup() {
        final Handler closePopupCont = new Handler();
        int contHeight = number_cont.getMeasuredHeight();
        number_cont.animate().translationY((contHeight + 100)).setDuration(250);

        closePopupCont.postDelayed(new Runnable() {
            @Override
            public void run() {
                popup_cont_bg.animate().alpha(0f).setDuration(300);
                popup_cont.setVisibility(View.GONE);
            }
        }, 300);


    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }


    public void makeCall() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(((Activity) context), Manifest.permission.CALL_PHONE)) {
                    new AlertDialog.Builder(context)
                            .setTitle("Permission Needed")
                            .setMessage("Permission is needed to make call from your device...")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).create().show();
                } else {
                    ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION);
                }
                return;
            }
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mNumber));
        startActivity(intent);
        closeContactPopup();

    }




    /*private void requestCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(context)
                    .setTitle("Permission Needed")
                    .setMessage("Permission is needed to make call from your device...")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION);
        }
    }*/

    /*private void makeCall() {
        //Toast.makeText(context,mNumber,Toast.LENGTH_LONG).show();
        *//*Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + mNumber));
        startActivity(intent);
        closeContactPopup();
*//*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                //if (ActivityCompat.shouldShowRequestPermissionRationale(((Activity) context), Manifest.permission.CALL_PHONE)) {

                    *//*Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + mNumber));
                    startActivity(intent);*//*

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + mNumber));
                    startActivity(intent);
                    closeContactPopup();

                *//*} else {
                    requestCallPermission();
                    //ActivityCompat.requestPermissions(((Activity) context), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PERMISSION);
                }*//*
            } else {
                requestCallPermission();
            }
        }
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Thanks for enabling the permission", Toast.LENGTH_SHORT).show();
                //do something permission is allowed here....

                makeCall();


            } else {
                Toast.makeText(context, "Please allow the Permission", Toast.LENGTH_SHORT).show();
            }
        }

    }


}




