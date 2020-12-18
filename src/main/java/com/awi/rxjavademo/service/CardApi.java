package com.awi.rxjavademo.service;

import com.awi.rxjavademo.model.CardResponse;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface CardApi {

    @GET("/card")
    Single<CardResponse> getCardInfo();
}
