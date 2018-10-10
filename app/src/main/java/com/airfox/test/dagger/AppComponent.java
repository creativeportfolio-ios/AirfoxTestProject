package com.airfox.test.dagger;



import com.airfox.test.dagger.module.AppModule;
import com.airfox.test.dagger.module.NetworkModule;
import com.airfox.test.ui.album.AlbumActivity;
import com.airfox.test.ui.albumdetails.AlbumDetailsActivity;
import com.airfox.test.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(AlbumActivity albumActivity);

    void inject(AlbumDetailsActivity albumDetailsActivity);

}
