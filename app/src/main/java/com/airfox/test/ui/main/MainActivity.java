package com.airfox.test.ui.main;

import android.content.Intent;

import com.airfox.test.R;
import com.airfox.test.ui.album.AlbumActivity;
import com.airfox.test.ui.base.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @Override
    protected boolean isFullScreen() {
        return false;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initControl() {
        getComponent().inject(this);
    }

    @Override
    protected void setListener() {

    }

    @OnClick(R.id.btnShowAlbum)
    public void onShowAlbumButtonClick() {
        startActivity(new Intent(MainActivity.this, AlbumActivity.class));
    }
}
