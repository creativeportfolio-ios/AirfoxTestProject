package com.airfox.test.ui.album;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.airfox.test.R;
import com.airfox.test.adapter.AlbumAdapter;
import com.airfox.test.api.Service;
import com.airfox.test.api.response.Album;
import com.airfox.test.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AlbumActivity extends BaseActivity implements AlbumView {

    @Inject
    Service service;

    @BindView(R.id.albumRV)
    RecyclerView albumRV;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    AlbumPresenter albumPresenter;
    AlbumAdapter albumAdapter;

    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_album;
    }

    @Override
    protected void initControl() {
        getComponent().inject(this);

        albumPresenter = new AlbumPresenterImpl(this, service, this);

        albumRV.setHasFixedSize(true);
        albumRV.setLayoutManager(new LinearLayoutManager(this));

        albumPresenter.getAlbums();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onAlbumSuccess(List<Album> albumList) {
        progressBar.setVisibility(View.GONE);
        albumAdapter = new AlbumAdapter(this, albumList);
        albumRV.setAdapter(albumAdapter);
    }

    @Override
    public void onInternetConnectionError() {
        progressBar.setVisibility(View.GONE);
    }
}
