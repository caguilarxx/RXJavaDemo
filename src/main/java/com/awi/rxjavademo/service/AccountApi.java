package com.awi.rxjavademo.service;

import com.awi.rxjavademo.model.Account;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AccountApi {

    @GET("/account/{accountNumber}")
    Single<Account> getAccountInfo(@Path("accountNumber") String accountNumber);
}
