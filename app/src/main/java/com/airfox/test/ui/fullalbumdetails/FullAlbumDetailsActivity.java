package com.airfox.test.ui.fullalbumdetails;

import android.view.MenuItem;
import android.widget.ImageView;

import com.airfox.test.R;
import com.airfox.test.api.response.AlbumDetails;
import com.airfox.test.ui.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;

public class FullAlbumDetailsActivity extends BaseActivity {

    public static String ARG_ALBUM_DETAILS = "arg_album_details";

    @BindView(R.id.imgAlbum)
    ImageView imgAlbum;

    private AlbumDetails albumDetails;

    @Override
    protected boolean isFullScreen() {
        return true;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_full_album_details;
    }

    @Override
    protected void initControl() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        albumDetails = (AlbumDetails) getIntent().getSerializableExtra(ARG_ALBUM_DETAILS);

        getSupportActionBar().setTitle(albumDetails.getTitle());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_background);
        requestOptions.error(R.drawable.ic_launcher_background);

        Glide.with(this)
                .load(albumDetails.getUrl())
                .apply(requestOptions)
                .into(imgAlbum);
    }

    @Override
    protected void setListener() {

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
