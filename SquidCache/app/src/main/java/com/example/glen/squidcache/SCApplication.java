package com.example.glen.squidcache;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by glen on 8/30/17.
 */
public class SCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
