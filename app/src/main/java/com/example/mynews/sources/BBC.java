package com.example.mynews.sources;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


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


public class BBC extends Fragment {
    private RecyclerView BBCArticleList;
    private RecyclerView.Adapter mBBCAdapter;
    List<Article> bbcArticleList = new ArrayList<>();
    private RecyclerView.LayoutManager BBCArticleListLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_bbc, container, false);
        BBCArticleList=(RecyclerView) v.findViewById(R.id.articleLayout);
        BBCArticleList.setNestedScrollingEnabled (false);
        BBCArticleList.setHasFixedSize(false);
        BBCArticleListLayoutManager=new LinearLayoutManager(v.getContext(), RecyclerView.VERTICAL,false);
        BBCArticleList.setLayoutManager(BBCArticleListLayoutManager);
        mBBCAdapter=new BBCAdapter(bbcArticleList);
        BBCArticleList.setAdapter(mBBCAdapter);
        final APIInterface apiService = APIClient.getClient().create(APIInterface.class);
        Call<ResponseModel> call = apiService.getLatestNews("bbc-news", API_KEY);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel>call, Response<ResponseModel> response) {
                if(response.body().getStatus().equals("ok")) {
                    List<Article> articleList = response.body().getArticles();
                    for(Article article:articleList) {
                        bbcArticleList.add(article);
                        mBBCAdapter.notifyDataSetChanged();
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