package com.airfox.test.api;

import com.airfox.test.api.response.Album;
import com.airfox.test.api.response.AlbumDetails;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class Service {

    private final AirfoxApi airfoxApi;

    public Service(AirfoxApi airfoxApi) {
        this.airfoxApi = airfoxApi;
    }


    public Disposable getAlbums(final ResponseListener<List<Album>, String> listener) {
        return airfoxApi.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ApiResponseCallbackWrapper<Response<List<Album>>>() {

                    @Override
                    protected void onSuccess(Response<List<Album>> listResponse) {
                        if (listResponse.code() == 200 || listResponse.code() == 201)
                            listener.onSuccess(listResponse.body());
                    }

                    @Override
                    protected void onInternetConnectionError() {
                        listener.onInternetConnectionError();
                    }

                    @Override
                    protected void onFailure(String error) {
                        listener.onFailure(error);
                    }
                });
    }

    public Disposable getAlbumsPhotos(final ResponseListener<List<AlbumDetails>, String> listener) {
        return airfoxApi.getAlbumsPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ApiResponseCallbackWrapper<Response<List<AlbumDetails>>>() {

                    @Override
                    protected void onSuccess(Response<List<AlbumDetails>> listResponse) {
                        if (listResponse.code() == 200 || listResponse.code() == 201)
                            listener.onSuccess(listResponse.body());
                    }

                    @Override
                    protected void onInternetConnectionError() {
                        listener.onInternetConnectionError();
                    }

                    @Override
                    protected void onFailure(String error) {
                        listener.onFailure(error);
                    }
                });
    }
}
