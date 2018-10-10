package com.airfox.test.api;

import java.net.UnknownHostException;

import javax.net.ssl.SSLException;

import io.reactivex.observers.DisposableObserver;

public abstract class ApiResponseCallbackWrapper<T> extends DisposableObserver<T> {

    protected abstract void onSuccess(T t);

    protected abstract void onInternetConnectionError();

    protected abstract void onFailure(String error);

    @Override
    public void onNext(T value) {
        onSuccess(value);
    }

    @Override
    public void onError(Throwable error) {
        if (error instanceof ConnectivityInterceptor.NoConnectivityException || error instanceof UnknownHostException || error instanceof SSLException) {
            onInternetConnectionError();
        } else {
            onFailure(error.getLocalizedMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
