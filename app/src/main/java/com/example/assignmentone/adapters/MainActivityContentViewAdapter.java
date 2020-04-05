package com.example.assignmentone.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentone.R;
import com.example.assignmentone.modals.MainActivityContentViewContactList;
import com.example.assignmentone.modals.MainActivityContentViewList;
import com.example.assignmentone.modals.emergency.Contacts;
import java.util.ArrayList;
import java.util.List;

public class MainActivityContentViewAdapter extends RecyclerView.Adapter<MainActivityContentViewAdapter.ContentVH> {

    private Context context;
    private List<MainActivityContentViewList> mainActivityContentViewLists;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onContactItemClick(View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MainActivityContentViewAdapter(Context context, List<MainActivityContentViewList> mainActivityContentViewLists) {
        this.context = context;
        this.mainActivityContentViewLists = mainActivityContentViewLists;
    }

    @NonNull
    @Override
    public ContentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item_view_recycler_view_layout, parent, false);
        return new MainActivityContentViewAdapter.ContentVH(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentVH holder, int position) {
        final MainActivityContentViewList items = mainActivityContentViewLists.get(position);


        holder.content_title.setText(items.getTitle());

        if (!items.getDistanceApprox().equalsIgnoreCase("")) {
            String approxDistance = "Approx. " + items.getDistanceApprox() + " km";
            holder.content_address_icon_cont.setVisibility(View.VISIBLE);
            holder.content_address_icon_cont.setTag(R.string.lat, items.getAddressLat());
            holder.content_address_icon_cont.setTag(R.string.lng, items.getAddressLng());
            holder.content_distance.setText(approxDistance);
        } else {
            holder.content_address_icon_cont.setVisibility(View.GONE);
        }


        holder.content_address_icon_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat, lng;
                lat = v.getTag(R.string.lat).toString();
                lng = v.getTag(R.string.lng).toString();

                //String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
                String uri = "http://maps.google.com/maps?q=loc:" + items.getAddressLat() + "," + items.getAddressLng() + " (" + items.getTitle() + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                context.startActivity(intent);


            }
        });

        if (!items.getFullAddress().equalsIgnoreCase("")) {
            holder.content_address.setText(items.getFullAddress());
        } else {
            holder.content_address.setVisibility(View.GONE);
            holder.content_address_icon_cont.setVisibility(View.GONE);
        }

        List<Contacts> contactsList = items.getContacts();

        for (Contacts contacts : contactsList) {

            int cId = contacts.getEmergency_contact_id();
            String name = contacts.getName();
            String primary_contact_no = contacts.getPrimary_contact_no();
            String secondary_contact_no = contacts.getSecondary_contact_no();

            holder.contacts.add(new MainActivityContentViewContactList(cId, name, primary_contact_no, secondary_contact_no));
        }


        //holder.contacts.add(new MainActivityContentViewContactList(1, items.getContacts(),"6876876689","347894579485"));


    }

    @Override
    public int getItemCount() {
        return mainActivityContentViewLists.size();
    }

    public class ContentVH extends RecyclerView.ViewHolder {
        LinearLayout content_address_icon_cont;
        TextView content_title, content_address, content_distance;
        RecyclerView contactRecyclerView;
        MainActivityContentViewContactAdapter adapter;
        List<MainActivityContentViewContactList> contacts = new ArrayList<>();

        public ContentVH(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            content_address_icon_cont = itemView.findViewById(R.id.content_address_icon_cont);
            content_title = itemView.findViewById(R.id.content_title);
            content_address = itemView.findViewById(R.id.content_address);
            content_distance = itemView.findViewById(R.id.content_distance);
            contactRecyclerView = itemView.findViewById(R.id.content_contacts);


            contactRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            //main_content_recycler_view.setLayoutManager(new GridLayoutManager(context, 2));
            contactRecyclerView.setHasFixedSize(true);
            adapter = new MainActivityContentViewContactAdapter(context, contacts);
            contactRecyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new MainActivityContentViewContactAdapter.OnItemClickListener() {
                @Override
                public void onContactViewClick(View v, int position) {
                    if (listener != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            listener.onContactItemClick(v);
                        }
                    }
                }
            });


        }
    }
}
