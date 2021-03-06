package com.yausername.youtubedl_android_example;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.yausername.youtubedl_android.BuildConfig;
import com.yausername.youtubedl_android.YoutubeDL;
import com.yausername.youtubedl_android.YoutubeDLException;

import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;

public class App extends Application {

    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();

        configureRxJavaErrorHandler();

        initLibraries();
    }

    private void configureRxJavaErrorHandler() {
        RxJavaPlugins.setErrorHandler(e -> {

            if (e instanceof UndeliverableException) {
                // As UndeliverableException is a wrapper, get the cause of it to get the "real" exception
                e = e.getCause();
            }

            if (e instanceof InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return;
            }

            Log.e(TAG, "Undeliverable exception received, not sure what to do", e);
        });
    }

    private void initLibraries() {
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });

        try {
            YoutubeDL.getInstance().init(this);
        } catch (YoutubeDLException e) {
            Logger.e(e, "failed to initialize youtubedl-android");
        }
    }

}
