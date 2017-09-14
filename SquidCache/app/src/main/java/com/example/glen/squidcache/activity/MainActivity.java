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
import com.facebook.drawee.view.SimpleDraweeView;

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

//    @BindView(R.id.a) SimpleDraweeView mA;
//    @BindView(R.id.b) SimpleDraweeView mB;
//    @BindView(R.id.c) SimpleDraweeView mC;
//    @BindView(R.id.d) SimpleDraweeView mD;
//    @BindView(R.id.e) SimpleDraweeView mE;
//    @BindView(R.id.f) SimpleDraweeView mF;

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
//        mA.setImageURI("http://banffnationalpark.com/wp-content/uploads/2017/03/banff-casino.jpg");
//        mA.setImageURI("http://d27ch2ffih4nl.cloudfront.net/v2/9e1f63/200/tm.png");
//        mB.setImageURI("http://ywcabanff.ca/wp-content/uploads/2015/01/banff-park-alberta-canada-e1422913673198.jpg");
//        mC.setImageURI("http://banff.ca/images/pages/N89/12_106DWEB.jpg");
//        mD.setImageURI("http://www.fairmont.com/assets/0/137/9453/9512/9514/14591/59c67e88-6ad2-4cde-888b-ef4af0a2644b.jpg");
//        mE.setImageURI("http://banffnationalpark.com/wp-content/uploads/2014/08/lake-minnewanka-banff-national-park-alberta.jpg");
//        mF.setImageURI("http://www.nationalgeographic.com/content/dam/travel/photos/000/367/36732.jpg");

        ImageAdapter ia = new ImageAdapter(this, Arrays.asList(mUrls));
        ia.setClickListener(this);
        mImages.setLayoutManager(new GridLayoutManager(this, COLS));
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
