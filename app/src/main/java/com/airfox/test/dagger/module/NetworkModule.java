package com.airfox.test.dagger.module;

import android.app.Application;

import com.airfox.test.BuildConfig;
import com.airfox.test.api.AirfoxApi;
import com.airfox.test.api.ConnectivityInterceptor;
import com.airfox.test.api.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(Application application) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);  // <-- this is the important line!
        }
        httpClient.interceptors().add(new ConnectivityInterceptor());
        httpClient.connectTimeout(1, TimeUnit.MINUTES).writeTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES);

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    public Service providesService(Retrofit retrofit) {
        return new Service(retrofit.create(AirfoxApi.class));
    }

}
