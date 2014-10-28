package com.davidofffarchik.webclient;


public interface WebClientListener<T> {

    public void onResponseSuccess(T result);
    public void onResponseError();

}
