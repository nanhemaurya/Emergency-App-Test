package com.example.assignmentone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.assignmentone.adapters.MainActivityContentAdapter;
import com.example.assignmentone.interfaces.JSONApi;
import com.example.assignmentone.methods.CommonMethods;
import com.example.assignmentone.methods.ToolbarMethods;
import com.example.assignmentone.modals.emergency.Emergency;
import com.example.assignmentone.modals.MainActivityContentList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Context context;
    private ProgressDialog progressDialog;
    private MainActivityContentAdapter adapter;
    private List<MainActivityContentList> contents = new ArrayList<>();

    private List<Emergency> emergency;
    ToolbarMethods toolbarMethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;


        toolbarMethods = new ToolbarMethods(this);
        toolbarMethods.setTitle(getResources().getString(R.string.emergency));

        CommonMethods.checkConnection(context);

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://18.139.219.127:49179/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JSONApi jsonApi = retrofit.create(JSONApi.class);

        Call<List<Emergency>> call = jsonApi.getEmergency();
        call.enqueue(new Callback<List<Emergency>>() {
            @Override
            public void onResponse(Call<List<Emergency>> call, Response<List<Emergency>> response) {

                progressDialog.hide();

                if (!response.isSuccessful()) {
                    response.code();
                    return;
                }
                emergency = response.body();


                // Convert String Array to List
                final List<Integer> contactType;
                contactType = new ArrayList<>();


                for (Emergency emer : emergency) {
                    if (!contactType.contains(emer.getContact_type())) {
                        contactType.add(emer.getContact_type());
                    }

                }

                for (Integer cnTyp : contactType) {
                    System.out.println("c------ " + contactType);
                    String title, shortDesc = "", desc = "";
                    int count = 0;
                    double greaterVal = 0.0;

                    List<String> names = new ArrayList<>();

                    switch (cnTyp) {
                        case 1:
                            title = "Police Stations";
                            break;
                        case 2:
                            title = "Hospitals";
                            break;
                        default:
                            title = "Unknown";
                            break;
                    }


                    for (Emergency emer : emergency) {
                        if (emer.getContact_type().equals(cnTyp)) {
                            String name = emer.getName();
                            if (name.contains(" ")) {
                                name = name.substring(0, name.indexOf(" "));
                            }
                            names.add(name);
                            if (emer.getDistance_km() > greaterVal) {
                                greaterVal = emer.getDistance_km();
                            }

                            count = count + 1;
                        }
                    }

                    if (count > 1) {
                        shortDesc = count + " within " + greaterVal;

                        desc += "Nearest: ";
                        for (String string : names) {
                            desc += string + ", ";
                        }
                    } else if (count == 1) {
                        shortDesc = count + " nearby ";
                    }


                    contents.add(new MainActivityContentList(cnTyp, title, shortDesc, desc));
                }

                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Emergency>> call, Throwable t) {
                System.out.println(t.getMessage());
                progressDialog.hide();
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });





       /* contents.add(new MainActivityContentList(1, "Hospitals", "2 within 10km", "Nearest: Max, Vaishali"));
        contents.add(new MainActivityContentList(2, "Police Stations", "1 nearby", ""));
        */
        //contents.add(new MainActivityContentList(3, "Fire Stations", "2 within 10km", ""));


        RecyclerView main_content_recycler_view = findViewById(R.id.main_content_recycler_view);
        //main_content_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        main_content_recycler_view.setLayoutManager(new GridLayoutManager(context, 2));
        main_content_recycler_view.setHasFixedSize(true);

        adapter = new MainActivityContentAdapter(context, contents);
        main_content_recycler_view.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainActivityContentAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View v, int position) {
                String id = v.getTag(R.string.itemId).toString();
                String title = v.getTag(R.string.itemTitle).toString();
                gotoIntent(id, title, MainActivityItemView.class);
            }
        });


    }

    public void gotoIntent(String id, String title, Class cls) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("id", id);
        intent.putExtra("value", new Gson().toJson(emergency));
        intent.putExtra("title", title);
        context.startActivity(intent);
    }


}
