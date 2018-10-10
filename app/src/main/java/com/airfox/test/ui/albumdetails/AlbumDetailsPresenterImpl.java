package com.airfox.test.ui.albumdetails;

import android.content.Context;

import com.airfox.test.api.ResponseListener;
import com.airfox.test.api.Service;
import com.airfox.test.api.response.AlbumDetails;
import com.airfox.test.ui.base.BasePresenter;

import java.util.List;

public class AlbumDetailsPresenterImpl extends BasePresenter implements AlbumDetailsPresenter {

    Context context;
    Service service;
    AlbumDetailsView albumDetailsView;

    public AlbumDetailsPresenterImpl(Context context, Service service, AlbumDetailsView albumDetailsView) {
        this.context = context;
        this.service = service;
        this.albumDetailsView = albumDetailsView;
    }


    @Override
    public void getAlbumDetails() {
        compositeDisposable.add(service.getAlbumsPhotos(new ResponseListener<List<AlbumDetails>, String>() {
            @Override
            public void onSuccess(List<AlbumDetails> albumDetailsList) {
                albumDetailsView.onAlbumDetailsSuccess(albumDetailsList);
            }

            @Override
            public void onInternetConnectionError() {
                albumDetailsView.onInternetConnectionError();
            }

            @Override
            public void onFailure(String error) {

            }
        }));
    }
}
