package com.airfox.test.ui.album;

import android.content.Context;

import com.airfox.test.api.ResponseListener;
import com.airfox.test.api.Service;
import com.airfox.test.api.response.Album;
import com.airfox.test.ui.base.BasePresenter;

import java.util.List;

public class AlbumPresenterImpl extends BasePresenter implements AlbumPresenter {

    Context context;
    Service service;
    AlbumView albumView;

    public AlbumPresenterImpl(Context context, Service service, AlbumView albumView) {
        this.context = context;
        this.service = service;
        this.albumView = albumView;
    }


    @Override
    public void getAlbums() {
        compositeDisposable.add(service.getAlbums(new ResponseListener<List<Album>, String>() {
            @Override
            public void onSuccess(List<Album> albumList) {
                albumView.onAlbumSuccess(albumList);
            }

            @Override
            public void onInternetConnectionError() {
                albumView.onInternetConnectionError();
            }

            @Override
            public void onFailure(String error) {

            }
        }));
    }
}
