package com.example.mynews.sources;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.BuildConfig;
import com.example.mynews.MainActivity;
import com.example.mynews.R;
import com.example.mynews.api.APIClient;
import com.example.mynews.api.APIInterface;
import com.example.mynews.models.Article;
import com.example.mynews.models.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.mynews.APIKey.API_KEY;


public class Fox extends Fragment {
    private RecyclerView FoxArticleList;
    private RecyclerView.Adapter mFoxAdapter;
    String name;
    List<Article> foxArticleList = new ArrayList<>();
    private RecyclerView.LayoutManager FoxArticleListLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_fox, container, false);
        FoxArticleList=(RecyclerView) v.findViewById(R.id.articleLayout);
        FoxArticleList.setNestedScrollingEnabled (false);
        FoxArticleList.setHasFixedSize(false);
        FoxArticleListLayoutManager=new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL,false);
        FoxArticleList.setLayoutManager(FoxArticleListLayoutManager);
        mFoxAdapter=new FoxAdapter(foxArticleList);
        FoxArticleList.setAdapter(mFoxAdapter);
        final APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("fox-news", API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    for(Article article:articleList) {
                        foxArticleList.add(article);
                        mFoxAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseModel>call, Throwable t) {
                Log.e("out", t.toString());
            }
        });
        return v;
    }
}