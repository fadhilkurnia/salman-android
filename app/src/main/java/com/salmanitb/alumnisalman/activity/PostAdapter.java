/**
 * Created by nursyahrina on 03/03/18.
 */
package com.salmanitb.alumnisalman.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{

    private List<Post> postList;

    public class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView datetime, headline, content, contentfull, youtubeVideoID;
        public WebView webview;

        public PostViewHolder(View view) {
            super(view);
            datetime = (TextView) view.findViewById(R.id.datetime);
            headline = (TextView) view.findViewById(R.id.headline);
            content = (TextView) view.findViewById(R.id.content);
            contentfull = (TextView) view.findViewById(R.id.content_full);
            youtubeVideoID = (TextView) view.findViewById(R.id.youtube_video_ID);
            webview = (WebView) view.findViewById(R.id.webview_image);
        }
    }

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
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
        holder.webview.loadUrl(post.getImageLocation());
        holder.webview.setInitialScale(60);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

}
