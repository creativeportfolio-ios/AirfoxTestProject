package com.airfox.test.ui.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.airfox.test.App;
import com.airfox.test.dagger.AppComponent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        if (isFullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        if (setLayout() != 0) {
            setContentView(setLayout());
        } else {

        }

        unbinder = ButterKnife.bind(this);
        initControl();
        setListener();
    }

    public AppComponent getComponent() {
        return App.getApp().getComponent();
    }

    protected abstract boolean isFullScreen();

    protected abstract int setLayout();

    protected abstract void initControl();

    protected abstract void setListener();

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
