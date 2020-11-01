package com.example.mynews.sources;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mynews.ArticleWebView;
import com.squareup.picasso.Picasso;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mynews.R;
import com.example.mynews.models.Article;

import java.util.List;

public class BuzzfeedAdapter extends RecyclerView.Adapter<BuzzfeedAdapter.ViewHolder> {
    private List<Article> articleArrayList;

    public BuzzfeedAdapter(List<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    @Override
    public BuzzfeedAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.buzzfeed_item, viewGroup, false);
        return new BuzzfeedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BuzzfeedAdapter.ViewHolder viewHolder, int position) {
        final Article articleModel = articleArrayList.get(position);
        if (!TextUtils.isEmpty(articleModel.getTitle())) {
            viewHolder.mTitle.setText(articleModel.getTitle());
        }
        if (!TextUtils.isEmpty(articleModel.getPublishedAt())) {
            String mTimeString=articleModel.getPublishedAt().substring(0,10)+" "+articleModel.getPublishedAt().substring(11,19)+" UTC";
            viewHolder.mTime.setText(mTimeString);
        }
        if (articleModel.getUrlToImage()!=null) {
            Picasso.get().load(articleModel.getUrlToImage()).into(viewHolder.mThumbnail);
        }
        if (!TextUtils.isEmpty(articleModel.getDescription())) {
            viewHolder.mDesc.setText(articleModel.getDescription());
        }
        viewHolder.mLayout.setTag(articleModel);

        viewHolder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), ArticleWebView.class);
                Bundle bundle=new Bundle();
                bundle.putString("articleUrl",articleModel.getUrl());
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDesc;
        private LinearLayout mLayout;
        private ImageView mThumbnail;
        private TextView mTime;
        ViewHolder(View view) {
            super(view);
            mThumbnail = view.findViewById(R.id.thumbnail);
            mDesc = view.findViewById(R.id.description);
            mLayout = view.findViewById(R.id.layout);
            mTitle=view.findViewById(R.id.title);
            mTime=view.findViewById(R.id.time);
        }
    }
}