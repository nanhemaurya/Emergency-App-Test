package com.example.assignmentone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentone.R;
import com.example.assignmentone.modals.MainActivityContentViewContactList;

import java.util.List;

public class MainActivityContentViewContactAdapter extends RecyclerView.Adapter<MainActivityContentViewContactAdapter.ContactVH> {

    private Context context;
    private List<MainActivityContentViewContactList> mainActivityContentViewContactLists;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onContactViewClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public MainActivityContentViewContactAdapter(Context context, List<MainActivityContentViewContactList> mainActivityContentViewContactLists) {
        this.context = context;
        this.mainActivityContentViewContactLists = mainActivityContentViewContactLists;
    }

    @NonNull
    @Override
    public ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_item_view_recycler_view_contact_layout, parent, false);
        return new MainActivityContentViewContactAdapter.ContactVH(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactVH holder, int position) {

        MainActivityContentViewContactList items = mainActivityContentViewContactLists.get(position);
        String pNum = "", sNum = "";
        holder.contact_name.setText(items.getTitle());
        holder.contact_cont.setTag(R.string.contact_id, items.getId());
        if (items.getPrimaryNumber() != null) {
            pNum = items.getPrimaryNumber();
        }

        if (items.getSecondaryNumber() != null) {
            sNum = items.getSecondaryNumber();
        }

        holder.contact_cont.setTag(R.string.contact_pNumber, pNum);
        holder.contact_cont.setTag(R.string.contact_sNumber, sNum);


    }

    @Override
    public int getItemCount() {
        return mainActivityContentViewContactLists.size();
    }

    public class ContactVH extends RecyclerView.ViewHolder {

        TextView contact_name;
        LinearLayout contact_cont;

        public ContactVH(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_cont = itemView.findViewById(R.id.contact_cont);

            contact_cont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onContactViewClick(view, position);
                        }
                    }
                }
            });


        }
    }
}
