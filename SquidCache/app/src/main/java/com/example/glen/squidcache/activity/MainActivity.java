package com.example.glen.squidcache.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.glen.squidcache.R;
import com.example.glen.squidcache.adapter.ImageAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements ImageAdapter.ItemClickListener {

    //
    // Retrofit behind a proxy - https://stackoverflow.com/questions/32053413/using-retrofit-behind-a-proxy
    //

    //region Instance Variables

    private static final int COLS = 3;

    @BindView(R.id.images) RecyclerView mImages;

    private String[] mUrls;

    private Unbinder mUnbinder;

    //endregion

    //region Lifecycle methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrls = getResources().getStringArray(R.array.medium_urls);

        Log.i("ABC", "Number of Image Urls - " + mUrls.length);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadImages();
    }

    //endregion

    private void loadImages() {
        ImageAdapter ia = new ImageAdapter(this, Arrays.asList(mUrls));
        mImages.setLayoutManager(new GridLayoutManager(this, COLS));
        ia.setClickListener(this);
        mImages.setAdapter(ia);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.i("ABC", "Image Clicked - " + position);

        Intent intent = new Intent(this, ViewImageActivity.class);
        intent.putExtra(ViewImageActivity.URL_KEY, mUrls[position]);
        startActivity(intent);
    }
}
