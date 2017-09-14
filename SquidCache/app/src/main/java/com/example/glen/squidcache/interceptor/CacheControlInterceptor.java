package com.example.glen.squidcache.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by glen on 9/14/17.
 */
public class CacheControlInterceptor implements Interceptor {

    private static final int MAX_AGE = 604800 * 4; //roughly a month

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // Add Cache Control only for GET methods
        if (request.method().equals("GET")) {

            // 4 weeks stale
            request = request.newBuilder()
                             .header("Cache-Control", "public, max-stale=2419200")
                             .build();
        }

        Response originalResponse = chain.proceed(request);

        return originalResponse.newBuilder()
                               .header("Cache-Control", String.format("max-age=%d", MAX_AGE))
                               .build();
    }
}
