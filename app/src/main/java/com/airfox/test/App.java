package com.airfox.test;

import android.app.Application;
import android.content.Context;

import com.airfox.test.dagger.AppComponent;
import com.airfox.test.dagger.DaggerAppComponent;
import com.airfox.test.dagger.module.AppModule;
import com.airfox.test.dagger.module.NetworkModule;

public class App extends Application {

    public static App inst;
    private AppComponent component;

    public static App getApp() {
        return inst;
    }

    public Context getContext() {
        return inst.getApplicationContext();
    }

    public AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inst = this;

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }
}
