package com.airfox.test.api;

import com.airfox.test.api.response.Album;
import com.airfox.test.api.response.AlbumDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface AirfoxApi {

    @GET("albums")
    Observable<Response<List<Album>>> getAlbums();

    @GET("photos")
    Observable<Response<List<AlbumDetails>>> getAlbumsPhotos();
}
