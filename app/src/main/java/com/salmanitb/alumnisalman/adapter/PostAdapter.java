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
    private PostAdapter.OnItemClickListener listener;

    public PostAdapter(List<Post> postList, PostAdapter.OnItemClickListener listener) {
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
        holder.bind(postList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return postList.size();
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
        @BindView(R.id.content_full) TextView contentfull;
        @BindView(R.id.youtube_video_ID) TextView youtubeVideoID;
        public View layout;

        public PostViewHolder(View view) {
            super(view);
            layout = view;
            ButterKnife.bind(this,view);
        }

        public void bind(final Post post, final OnItemClickListener listener) {

            datetime.setText(post.getDatetime());
            headline.setText(post.getHeadline());
            content.setText(post.getContent().substring(0,149) + "...");
            contentfull.setText(post.getContent());
            youtubeVideoID.setText(post.getYoutubeVideoID());
            Picasso.get().load(post.getImageLocation()).fit().centerCrop().into(image);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(post);
                }
            });
        }

    }

}
