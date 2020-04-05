package com.example.assignmentone.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentone.MainActivityItemView;
import com.example.assignmentone.R;
import com.example.assignmentone.modals.MainActivityContentList;

import java.util.List;
import java.util.Random;

public class MainActivityContentAdapter extends RecyclerView.Adapter<MainActivityContentAdapter.MainContentVH> {

    private Context context;
    private List<MainActivityContentList> mainActivityContentLists;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View v,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public MainActivityContentAdapter(Context context, List<MainActivityContentList> mainActivityContentLists) {
        this.context = context;
        this.mainActivityContentLists = mainActivityContentLists;
    }

    @NonNull
    @Override
    public MainContentVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_recyclerview_list, parent, false);
        return new MainContentVH(itemView,mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull MainContentVH holder, final int position) {

        MainActivityContentList items = mainActivityContentLists.get(position);

        Random rnd = new Random();
        int currentColor = Color.argb(50, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        LayerDrawable layerDrawable = (LayerDrawable) holder.container.getBackground();
        GradientDrawable shape = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.shape_id);
        shape.setColor(currentColor);

        holder.title.setText(items.getTitle());
        holder.container.setTag(R.string.itemId,items.getId());
        holder.container.setTag(R.string.itemTitle,items.getTitle());
        holder.shortDesc.setText(items.getShortDesc());
        holder.desc.setText(items.getDesc());
        if(items.getShortDesc().equalsIgnoreCase("")){
            holder.shortDesc.setVisibility(View.GONE);
        }
        if(items.getDesc().equalsIgnoreCase("")){
            holder.desc.setVisibility(View.GONE);
        }




    }

    @Override
    public int getItemCount() {
        return mainActivityContentLists.size();
    }

    public class MainContentVH extends RecyclerView.ViewHolder {

        TextView title, shortDesc, desc;
        LinearLayout container;

        public MainContentVH(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            title = itemView.findViewById(R.id.main_content_title);
            shortDesc = itemView.findViewById(R.id.main_content_short_desc);
            desc = itemView.findViewById(R.id.main_content_desc);
            container = itemView.findViewById(R.id.main_content_container);


            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(v,position);
                        }
                    }

//                    int position = getLayoutPosition();
//                    Toast.makeText(context,position,Toast.LENGTH_LONG).show();
//                    context.startActivity(new Intent(context, MainActivityItemView.class));

                }

            });


        }
    }
}
