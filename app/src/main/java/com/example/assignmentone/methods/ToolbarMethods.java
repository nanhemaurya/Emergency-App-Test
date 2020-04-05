package com.example.assignmentone.methods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmentone.MainActivity;
import com.example.assignmentone.R;

public class ToolbarMethods {
    private Context context;
    private Activity activity;
    private TextView toolbar_back_btn, toolbar_title;
    private ImageView toolbar_menu_btn, toolbar_home_btn;

    public ToolbarMethods(Context context) {
        this.context = context;
        init();
    }


    private void init() {
        activity = ((Activity) context);

        toolbar_title = activity.findViewById(R.id.toolbar_title);
        toolbar_title.setText(R.string.app_name);

        toolbar_back_btn = activity.findViewById(R.id.toolbar_back_btn);
        toolbar_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {activity.finish();}
        });


        toolbar_menu_btn = activity.findViewById(R.id.toolbar_menu_btn);



        toolbar_home_btn = activity.findViewById(R.id.toolbar_home_btn);
        toolbar_home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {gotoIntent(MainActivity.class);}
        });


    }

    public void allowBackButton(boolean isAllowed) {
        if (isAllowed) {
            toolbar_menu_btn.setVisibility(View.GONE);
            toolbar_back_btn.setVisibility(View.VISIBLE);
        } else {
            toolbar_menu_btn.setVisibility(View.VISIBLE);
            toolbar_back_btn.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title) {
        toolbar_title.setText(title);
    }

    private void gotoIntent(Class cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

}
