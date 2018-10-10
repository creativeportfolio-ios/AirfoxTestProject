package com.airfox.test.ui.albumdetails;

import com.airfox.test.api.response.AlbumDetails;
import com.airfox.test.ui.base.BaseView;

import java.util.List;

public interface AlbumDetailsView extends BaseView {
    void onAlbumDetailsSuccess(List<AlbumDetails> albumDetailsList);
}
