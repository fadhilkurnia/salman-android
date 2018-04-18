/**
 * Created by nursyahrina on 03/03/18.
 */
package com.salmanitb.alumnisalman.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    final private int VIEWTYPE_HEADLINE = 1;
    final private int VIEWTYPE_NORMAL = 0;

    private List<Post> postList;
    private PostAdapter.OnItemClickListener listener;

    public PostAdapter(List<Post> postList, PostAdapter.OnItemClickListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEWTYPE_HEADLINE) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.salman_menyapa_headline_item, parent, false);
            return new PostHeadlineViewHolder(itemView);
        }
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salman_menyapa_item, parent, false);
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEWTYPE_HEADLINE) {
            PostHeadlineViewHolder headlineHolder = (PostHeadlineViewHolder) holder;
            headlineHolder.bind(postList.get(position), listener);
        } else {
            PostViewHolder normalHolder = (PostViewHolder) holder;
            normalHolder.bind(postList.get(position), listener);
        }

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 1 : headline, 0: other
        if (position == 0) return VIEWTYPE_HEADLINE;
        return VIEWTYPE_NORMAL;
    }


    public Post getPost(int index) {
        return postList.get(index);
    }

    public interface OnItemClickListener {
        public void onItemClick(Post post);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.webview_image) ImageView image;
        @BindView(R.id.datetime) TextView datetime;
        @BindView(R.id.headline) TextView headline;
        @BindView(R.id.content) TextView content;
        public View layout;

        public PostViewHolder(View view) {
            super(view);
            layout = view;
            ButterKnife.bind(this,view);
        }

        public void bind(final Post post, final OnItemClickListener listener) {

            datetime.setText(post.getDatetime());
            headline.setText(post.getHeadline());
            content.setText(post.getContent());
            Picasso.get().load(post.getImageLocation()).fit().centerCrop().into(image);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(post);
                }
            });
        }

    }

    public class PostHeadlineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.headline_image) ImageView headlineImage;
        @BindView(R.id.headline_title) TextView headlineTitle;
        @BindView(R.id.headline_time) TextView headlineTime;
        public View layout;

        public PostHeadlineViewHolder(View itemView) {
            super(itemView);
            layout = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Post post, final OnItemClickListener listener) {
            Picasso.get().load(post.getImageLocation()).fit().centerCrop().into(headlineImage);
            headlineTitle.setText(post.getHeadline());
            headlineTime.setText(post.getDatetime());

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(post);
                }
            });
        }
    }

}
