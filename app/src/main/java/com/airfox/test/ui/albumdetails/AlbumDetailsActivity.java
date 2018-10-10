package com.airfox.test.ui.albumdetails;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.airfox.test.R;
import com.airfox.test.adapter.AlbumDetailsAdapter;
import com.airfox.test.api.Service;
import com.airfox.test.api.response.Album;
import com.airfox.test.api.response.AlbumDetails;
import com.airfox.test.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AlbumDetailsActivity extends BaseActivity implements AlbumDetailsView {

    public static String ARG_ALBUM = "arg_album";

    @Inject
    Service service;

    @BindView(R.id.albumDetailsRV)
    RecyclerView albumDetailsRV;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private AlbumDetailsPresenter albumDetailsPresenter;
    private Album album;
    private AlbumDetailsAdapter albumDetailsAdapter;

    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_album_details;
    }

    @Override
    protected void initControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getComponent().inject(this);

        album = (Album) getIntent().getSerializableExtra(AlbumDetailsActivity.ARG_ALBUM);

        getSupportActionBar().setTitle(album.getTitle());

        albumDetailsPresenter = new AlbumDetailsPresenterImpl(this, service, this);

        albumDetailsRV.setHasFixedSize(true);
        albumDetailsRV.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
        albumDetailsPresenter.getAlbumDetails();
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onAlbumDetailsSuccess(List<AlbumDetails> albumDetailsList) {
        progressBar.setVisibility(View.GONE);
        if (albumDetailsList != null && !albumDetailsList.isEmpty()) {
            albumDetailsAdapter = new AlbumDetailsAdapter(this, getFilterDataByAlbumId(albumDetailsList));
            albumDetailsRV.setAdapter(albumDetailsAdapter);
        }
    }

    @Override
    public void onInternetConnectionError() {
        progressBar.setVisibility(View.GONE);
    }

    public List<AlbumDetails> getFilterDataByAlbumId(List<AlbumDetails> albumDetailsList) {
        List<AlbumDetails> filterAlbumDetails = new ArrayList<>();
        for (AlbumDetails albumDetails : albumDetailsList) {
            if (albumDetails.getAlbumId().equals(album.getId())) {
                filterAlbumDetails.add(albumDetails);
            }
        }
        return filterAlbumDetails;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
