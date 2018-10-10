package com.airfox.test.ui.album;

import com.airfox.test.api.response.Album;
import com.airfox.test.ui.base.BaseView;

import java.util.List;

public interface AlbumView extends BaseView {
    void onAlbumSuccess(List<Album> albumList);
}
