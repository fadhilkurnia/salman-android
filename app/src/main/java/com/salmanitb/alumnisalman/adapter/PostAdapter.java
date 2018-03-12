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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private List<Post> postList;
    PostClickListener listener;

    public class PostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.webview_image) ImageView image;
        @BindView(R.id.datetime) TextView datetime;
        @BindView(R.id.headline) TextView headline;
        @BindView(R.id.content) TextView content;
        @BindView(R.id.content_full) TextView contentfull;
        @BindView(R.id.youtube_video_ID) TextView youtubeVideoID;

        public PostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        public void onClick(View view) {
            listener.ItemClicked(view, this.getPosition());
        }
    }

    public PostAdapter(List<Post> postList, PostClickListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.salman_menyapa_item, parent, false);

        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.datetime.setText(post.getDatetime());
        holder.headline.setText(post.getHeadline());
        holder.content.setText(post.getContent().substring(0,149) + "...");
        holder.contentfull.setText(post.getContent());
        holder.youtubeVideoID.setText(post.getYoutubeVideoID());
        Picasso.get().load(post.getImageLocation()).fit().centerCrop().into(holder.image);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public Post getPost(int index) {
        return postList.get(index);
    }

    public interface PostClickListener {
        public void ItemClicked(View v, int position);
    }

}
