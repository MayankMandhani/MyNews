package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

import com.example.mynews.sources.Ars;
import com.example.mynews.sources.Bloomberg;
import com.example.mynews.sources.CBS;
import com.example.mynews.sources.CNN;
import com.example.mynews.sources.Buzzfeed;
import com.example.mynews.sources.Fox;
import com.example.mynews.sources.BBC;
import com.example.mynews.sources.NBC;
import com.example.mynews.sources.Reuters;
import com.example.mynews.sources.Washington;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.sources);
        SourcesAdapter adapter = new SourcesAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        addList(adapter);
    }
    void addList(SourcesAdapter adapter){
        adapter.addFragment(new Ars(), "Ars Technica");
        adapter.addFragment(new BBC(), "BBC News");
        adapter.addFragment(new Bloomberg(), "Bloomberg");
        adapter.addFragment(new Buzzfeed(), "Buzzfeed");
        adapter.addFragment(new CBS(), "CBS News");
        adapter.addFragment(new CNN(), "CNN");
        adapter.addFragment(new Fox(), "Fox News");
        adapter.addFragment(new NBC(), "NBC News");
        adapter.addFragment(new Reuters(), "Reuters");
        adapter.addFragment(new Washington(), "The Washington Post");
        adapter.notifyDataSetChanged();
    }
}