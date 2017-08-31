package com.example.glen.squidcache;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.net.InetSocketAddress;
import java.net.Proxy;

import okhttp3.OkHttpClient;

/**
 * Created by glen on 8/30/17.
 */
public class SCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Proxy proxyTest = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("192.168.0.121", 3128));

        OkHttpClient.Builder builder = new OkHttpClient.Builder().proxy(proxyTest);
        OkHttpClient client = builder.build();

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this, client).build();

        Fresco.initialize(this, config);
    }
}
