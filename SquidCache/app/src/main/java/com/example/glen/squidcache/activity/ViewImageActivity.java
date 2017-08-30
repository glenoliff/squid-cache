package com.example.glen.squidcache.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.glen.squidcache.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ViewImageActivity extends AppCompatActivity {

    //region Instance Variables

    public static final String URL_KEY = "UrlKey";

    @BindView(R.id.image) SimpleDraweeView mImage;

    private String mUrl;

    private Unbinder mUnbinder;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnbinder = ButterKnife.bind(this);
        mUrl = getIntent().getExtras().getString(URL_KEY);
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
        mImage.setImageURI(mUrl);
    }
}
