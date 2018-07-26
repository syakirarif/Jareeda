package id.amoled.newsgits.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import id.amoled.newsgits.Adapter.AdapterNews;
import id.amoled.newsgits.Common.Common;
import id.amoled.newsgits.Interface.NewsService;
import id.amoled.newsgits.Model.WebSite;
import id.amoled.newsgits.R;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceActivity extends AppCompatActivity {

    private RecyclerView listBusiness;
    private NewsService mService;
    private AdapterNews adapter;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);

        Paper.init(this);

        mService = Common.getNewsService();

        listBusiness = findViewById(R.id.list_science);
        listBusiness.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listBusiness.setLayoutManager(layoutManager);

        swipeLayout = findViewById(R.id.layout_swipe_science);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadArticleSource(true);
            }
        });

        loadArticleSource(false);
    }

    private void loadArticleSource(boolean isRefreshed) {

        if (!isRefreshed) {
            swipeLayout.setRefreshing(true);

            String cache = Paper.book().read("img_science"); //GANTI INI

            if (cache != null && !cache.isEmpty() && !cache.equals("null")) {
                WebSite webSite = new Gson().fromJson(cache, WebSite.class);
                adapter = new AdapterNews(getBaseContext(), webSite);
                adapter.notifyDataSetChanged();
                listBusiness.setAdapter(adapter);
                swipeLayout.setRefreshing(false);
            } else {
                mService.getScience().enqueue(new Callback<WebSite>() { //GANTI INI
                    @Override
                    public void onResponse(@NonNull Call<WebSite> call, @NonNull Response<WebSite> response) {
                        adapter = new AdapterNews(getBaseContext(), response.body());
                        adapter.notifyDataSetChanged();
                        listBusiness.setAdapter(adapter);

                        Paper.book().write("img_science", new Gson().toJson(response.body())); //GANTI INI
                        swipeLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(@NonNull Call<WebSite> call, @NonNull Throwable t) {
                        Toast.makeText(ScienceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        swipeLayout.setRefreshing(false);
                    }
                });
            }

        } else {

            swipeLayout.setRefreshing(true);
            mService.getScience().enqueue(new Callback<WebSite>() { //GANTI INI
                @Override
                public void onResponse(@NonNull Call<WebSite> call, @NonNull Response<WebSite> response) {
                    adapter = new AdapterNews(getBaseContext(), response.body());
                    adapter.notifyDataSetChanged();
                    listBusiness.setAdapter(adapter);

                    Paper.book().write("img_science", new Gson().toJson(response.body())); //GANTI INI
                    swipeLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(@NonNull Call<WebSite> call, @NonNull Throwable t) {
                    Toast.makeText(ScienceActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    swipeLayout.setRefreshing(false);
                }
            });

        }


    }
}
