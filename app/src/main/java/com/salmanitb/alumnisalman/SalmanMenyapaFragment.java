package com.salmanitb.alumnisalman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SalmanMenyapaFragment extends Fragment {

    private List<Post> postList = new ArrayList<Post>();
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_salman_menyapa, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_salman_menyapa);

        postAdapter = new PostAdapter(postList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(postAdapter);

        preparePostData();
        return rootView;
    }

    private void preparePostData() {
        Post post = new Post("Kamis, 1 Maret 2018", "KALAM Salman: Keterhubungan Antar Alumni Itu Penting", "5xUCnEixL-s",
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
                "https://itb.ac.id/files/107/20140628/1403916610.jpg");
        postList.add(post);
        post = new Post(post.getDatetime(), post.getHeadline()+" I", post.getYoutubeVideoID(), post.getContent(), post.getImageLocation());
        postList.add(post);
        post = new Post(post.getDatetime(), post.getHeadline()+"I", "", post.getContent(), post.getImageLocation());
        postList.add(post);
        post = new Post(post.getDatetime(), post.getHeadline()+"I", "", post.getContent(), post.getImageLocation());
        postList.add(post);

        postAdapter.notifyDataSetChanged();
    }
}
