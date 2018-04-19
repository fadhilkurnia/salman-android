package com.salmanitb.alumnisalman.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.salmanitb.alumnisalman.R;
import com.salmanitb.alumnisalman.activity.ReadPostActivity;
import com.salmanitb.alumnisalman.adapter.PostAdapter;
import com.salmanitb.alumnisalman.helper.APIConnector;
import com.salmanitb.alumnisalman.helper.ISwipeRefreshLayout;
import com.salmanitb.alumnisalman.model.Post;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class SalmanMenyapaFragment extends Fragment {

    @BindView(R.id.swipe_refresh)
    ISwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.salman_menyapa_recycler_view)
    IRecyclerView recyclerView;

    private ArrayList<Post> postList = new ArrayList<Post>();
    private PostAdapter postAdapter;
    private int currentPage = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_salman_menyapa, container, false);
        ButterKnife.bind(this, rootView);

        PostAdapter.OnItemClickListener listener = new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {
                Intent intent = new Intent(getActivity(), ReadPostActivity.class);
                intent.putExtra("POST", post);
                startActivity(intent);
            }
        };

        prepareListener();

        postAdapter = new PostAdapter(postList, listener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setIAdapter(postAdapter);

        if (currentPage == 0) {
            loadPostData(currentPage+1);
        }

        return rootView;
    }

    private void prepareListener() {
        recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                recyclerView.getLoadMoreFooterView().setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        // do nothing
                    }
                }, 1000);
                loadPostData(currentPage+1);
            }
        });
        recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("SALMAN_APP", "on refresh");
                resetData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("SALMAN_APP", "on refresh");
                resetData();
            }
        });
    }

    private void resetData() {
        currentPage = 0;
        postList.clear();
        postAdapter.clearData();
        postAdapter.notifyDataSetChanged();

        loadPostData(currentPage+1);
    }

    private void loadPostData(int pageNumber) {
        APIConnector.getInstance().getSalmanMenyapa(pageNumber, new APIConnector.ApiCallback<ArrayList<Post>>() {
            @Override
            public void onSuccess(ArrayList<Post> response) {
                postAdapter.addAll(response);
                recyclerView.getLoadMoreFooterView().setVisibility(View.GONE);
                recyclerView.setRefreshing(false);
                swipeRefreshLayout.setRefreshing(false);
                currentPage++;
            }

            @Override
            public void onFailure(Throwable t) {
                recyclerView.getLoadMoreFooterView().setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                if (t.getMessage().equals("Artikel tidak ditemukan.")) {
                    Toast.makeText(getActivity(), "Anda sudah di ujung Salman Menyapa", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void preparePostData() {

        Post post = new Post(
                "KALAM Salman: Keterhubungan Antar Alumni Itu Penting",
                "https://today.line.me/id/article/Tarik+Produk+dari+Pasar+Heinz+ABC+Indonesia+Investigasi+Temuan+Cacing-RemzMw",
                "Pembangunan jaringan merupakan hal yang vital dalam mengoptimalkan dakwah di Indonesia. Berkaitan dengan ini, Alumni Masjid Salman ITB seyogyanya senantiasa terhubung satu sama lain. Bahkan lebih lanjut lagi, saling membantu saat ada yang kesusahan.\n" +
                        "\n" +
                        "“Kita harus senantiasa bergandengan tangan, siapa yang jatuh, kita bantu. Lalu siapa yang naik, agar tidak lupa,” kata Ketua YPM Salman Syarif Hidayat, pada acara Pelantikan Pengurus Keluarga Alumni (KALAM) Salman ITB periode 2015-2019, Sabtu (24/1).\n" +
                        "\n" +
                        "Hal ini penting, mengingat potensi umat Muslim Indonesia yang tercerai berai. Menurut Ketua Majelis Wakil Keluarga (MWK) KALAM Muslimin Nasution, misi penyatuan potensi tadi baru bisa terpenuhi bila jaringan yang kokoh telah terbangun. Salman dapat mendirikan asosiasi masjid-masjid kampus. Setelahnya, bersama-sama merancang pendidikan karakter yang terintegrasi.\n" +
                        "\n" +
                        "“Tidak mungkin itu terjadi kalau Anda tidak membangun jaringan, dan bagaimana jaringan bisa terbangun kalau tidak punya pusatnya?” ujar Muslimin.\n" +
                        "\n" +
                        "Menanggapi hal di atas, Ketua KALAM terpilih Agung Harsoyo menyatakan, pihaknya akan merancang program untuk memfasilitasi sillaturrahim antar-elemen umat Islam, seperti organisasi masyarakat dan dewan-dewan masjid. Keterhubungan tersebut diharapkan dapat memberi solusi bagi permasalahan masyarakat.\n" +
                        "\n" +
                        "Salah satu contohnya, tentang produksi produk halal. Agung menggambarkan, bagaimana para ahli dari berbagai bidang keilmuan seperti kimia, teknik kimia, farmasi, dan biologi berkontribusi dalam proses produksi tersebut. Lebih jauh lagi, dari sana akan berdiri laboratorium-laboratorium penelitian terkait di seluruh Indonesia. Menurut Agung, sebenarnya KALAM bisa memiliki peran dalam hal ini.\n" +
                        "\n" +
                        "“Insya Allah kita menyatukan potensi-potensi itu demi memiliki kekuatan yang lebih, dibanding sendiri-sendiri,” tutupnya.\n" +
                        "\n" +
                        "KALAM Salman memang bervisi menjadi wadah pemersatu dan penggerak potensi alumni. Tujuannya, untuk terwujudnya pembinaan insan, pengembangan masyarakat, dan pembangunan peradaban. Sejak berdirinya pada tahun 2002 lalu, KALAM Salman berkomitmen untuk membangun jaringan alumni guna mewujudkan masyarakat madani berdasarkan nilai-nilai Islam. [ed: Dh]",
                "https://itb.ac.id/files/107/20140628/1403916610.jpg",
                121232312l,
                123,
                123,
                true
                );

        post = new Post(post.getTitle() + " I", post.getContentURL(), post.getShortContent(), post.getImageURL(), post.getCreatedAt(), post.getLoveCount(), post.getViewCount(), post.isLikedByMe());
        postAdapter.add(post);
        post = new Post(post.getTitle() + " II", post.getContentURL(), post.getShortContent(), post.getImageURL(), post.getCreatedAt(), post.getLoveCount(), post.getViewCount(), post.isLikedByMe());
        postAdapter.add(post);
        post = new Post(post.getTitle() + " III", post.getContentURL(), post.getShortContent(), post.getImageURL(), post.getCreatedAt(), post.getLoveCount(), post.getViewCount(), post.isLikedByMe());
        postAdapter.add(post);
        post = new Post(post.getTitle() + " IV", post.getContentURL(), post.getShortContent(), post.getImageURL(), post.getCreatedAt(), post.getLoveCount(), post.getViewCount(), post.isLikedByMe());
        postAdapter.add(post);
        post = new Post(post.getTitle() + " V", post.getContentURL(), post.getShortContent(), post.getImageURL(), post.getCreatedAt(), post.getLoveCount(), post.getViewCount(), post.isLikedByMe());
        postAdapter.add(post);

        postAdapter.notifyDataSetChanged();
    }
}
