package com.aviato.application;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;


public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        picassoBuilder.downloader(new OkHttp3Downloader(new OkHttpClient()));
        Picasso picasso = picassoBuilder.build();
        Picasso.setSingletonInstance(picasso);
    }
}
