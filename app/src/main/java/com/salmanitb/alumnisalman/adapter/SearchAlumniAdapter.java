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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.salmanitb.alumnisalman.helper.WebService.BASE_IMAGE_URL;

/**
 * Created by hilmi on 13/03/2018.
 */

public class SearchAlumniAdapter extends RecyclerView.Adapter<SearchAlumniAdapter.ViewHolder> {

    private ArrayList<User> values;
    private OnItemClickListener listener;
    private Context context;

    public SearchAlumniAdapter(Context context, ArrayList<User> data, OnItemClickListener listener) {
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

    public void addAll(ArrayList<User> data) {
        values.clear();
        values.addAll(data);
        notifyDataSetChanged();
    }

    public void removeAll() {
        values.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtEmail;
        public TextView txtCity;
        public ImageView imgPhoto;
        public View layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            txtName = (TextView) itemView.findViewById(R.id.txt_display_name);
            txtEmail= (TextView) itemView.findViewById(R.id.txt_email);
            txtCity = (TextView) itemView.findViewById(R.id.txt_address);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_photo);

        }

        public void bind(final User alumni, final OnItemClickListener listener) {
            txtName.setText(alumni.getName());
            txtEmail.setText(alumni.getEmail());
            txtCity.setText(alumni.getCity());

//            Log.d("IMAGE_URL", alumni.getImageURL());
            if (alumni.getImageURL() != null) {
                if (!alumni.getImageURL().equals("")) {
                    Picasso.get().load(BASE_IMAGE_URL + alumni.getImageURL()).into(imgPhoto);
                }  else {
                    Picasso.get().load(R.drawable.ic_person).into(imgPhoto);
                }
            }

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(alumni);
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(User alumni);
    }
}
