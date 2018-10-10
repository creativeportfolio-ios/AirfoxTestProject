package com.airfox.test.api;

public interface ResponseListener<S,E> {
    void onSuccess(S response);
    void onInternetConnectionError();
    void onFailure(E error);
}