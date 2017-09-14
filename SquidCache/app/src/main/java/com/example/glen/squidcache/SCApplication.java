package com.example.glen.squidcache;

import android.app.Application;

import com.example.glen.squidcache.interceptor.CacheControlInterceptor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by glen on 8/30/17.
 */
public class SCApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.9", 3128));
//        Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("192.168.0.121", 3128));

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient client = new OkHttpClient.Builder()
                                              .proxy(proxy)
                                              .addInterceptor(new CacheControlInterceptor())
                                              .addInterceptor(logging)
                                              .build();

        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this, client).build();

        Fresco.initialize(this, config);
    }
}
