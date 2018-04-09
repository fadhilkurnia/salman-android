package com.salmanitb.alumnisalman.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.model.User;

import java.util.List;

/**
 * Created by hilmi on 13/03/2018.
 */

public class SearchAlumniAdapter extends RecyclerView.Adapter<SearchAlumniAdapter.ViewHolder> {

    private List<User> values;
    private OnItemClickListener listener;
    private Context context;

    public SearchAlumniAdapter(Context context, List<User> data, OnItemClickListener listener) {
        this.context = context;
        this.values = data;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.search_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(values.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public void add(int position, User alumni) {
        values.add(alumni);
        notifyDataSetChanged();
    }

    public void addAll(List<User> data) {
        values.clear();
        values.addAll(data);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtEmail;
        public TextView txtAddress;
        public ImageView imgPhoto;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtName = (TextView) itemView.findViewById(R.id.txt_display_name);
            txtEmail= (TextView) itemView.findViewById(R.id.txt_email);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_photo);

        }

        public void bind(final User alumni, final OnItemClickListener listener) {
            txtName.setText(alumni.getName());
            txtEmail.setText(alumni.getEmail());

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(alumni);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(User tutor);
    }
}
