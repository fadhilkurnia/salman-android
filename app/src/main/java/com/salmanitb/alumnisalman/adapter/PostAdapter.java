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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    public void add(Post post) {
        postList.add(post);
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<Post> posts) {
        postList.addAll(posts);
        notifyDataSetChanged();
    }

    public void clearData() {
        postList.clear();
        notifyDataSetChanged();
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
        @BindView(R.id.news_like_count) TextView txtLikeCount;
        @BindView(R.id.news_view_count) TextView txtViewCount;
        public View layout;

        public PostViewHolder(View view) {
            super(view);
            layout = view;
            ButterKnife.bind(this,view);
        }

        public void bind(final Post post, final OnItemClickListener listener) {

            datetime.setText(decodeUnixTime(post.getCreatedAt()));
            headline.setText(post.getTitle());
            content.setText(post.getShortContent());
            String txtLove = String.valueOf(post.getLoveCount()) + " suka";
            String txtView = String.valueOf(post.getViewCount()) + " tayang";
            txtLikeCount.setText(txtLove);
            txtViewCount.setText(txtView);
            Picasso.get().load(post.getImageURL()).fit().centerCrop().into(image);

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
            Picasso.get().load(post.getImageURL()).fit().centerCrop().into(headlineImage);
            headlineTitle.setText(post.getTitle());
            headlineTime.setText(decodeUnixTime(post.getCreatedAt()));

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(post);
                }
            });
        }
    }


    public static String decodeUnixTime(long unixTime) {
        Date date = new Date(unixTime*1000);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        StringBuilder sb = new StringBuilder();
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                sb.append("Minggu");
                break;
            case Calendar.MONDAY:
                sb.append("Senin");
                break;
            case Calendar.TUESDAY:
                sb.append("Selasa");
                break;
            case Calendar.WEDNESDAY:
                sb.append("Rabu");
                break;
            case Calendar.THURSDAY:
                sb.append("Kamis");
                break;
            case Calendar.FRIDAY:
                sb.append("Jumat");
                break;
            default: // saturday
                sb.append("Sabtu");
                break;
        }

        sb.append(", ");
        sb.append(c.get(Calendar.DAY_OF_MONTH));
        sb.append(" ");

        switch (c.get(Calendar.MONTH)) {
            case Calendar.JANUARY:
                sb.append("Januari");
                break;
            case Calendar.FEBRUARY:
                sb.append("Februari");
                break;
            case Calendar.MARCH:
                sb.append("Maret");
                break;
            case Calendar.APRIL:
                sb.append("April");
                break;
            case Calendar.MAY:
                sb.append("Mei");
                break;
            case Calendar.JUNE:
                sb.append("Juni");
                break;
            case Calendar.JULY:
                sb.append("Juli");
                break;
            case Calendar.AUGUST:
                sb.append("Agustus");
                break;
            case Calendar.SEPTEMBER:
                sb.append("September");
                break;
            case Calendar.OCTOBER:
                sb.append("Oktober");
                break;
            case Calendar.NOVEMBER:
                sb.append("November");
                break;
            default: // december
                sb.append("Desemeber");
                break;
        }
        sb.append(" ");
        sb.append(c.get(Calendar.YEAR));


        return sb.toString();
//        SimpleDateFormat simpleDate = new SimpleDateFormat("EEEE, d MMMM y");
//        String formatedDate = simpleDate.format(date);
//        return  formatedDate;
//        return "Kamis, 1 Maret 2018";
    }

}
