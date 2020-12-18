package com.awi.rxjavademo.controller;

import com.awi.rxjavademo.model.Account;
import com.awi.rxjavademo.model.CardResponse;
import com.awi.rxjavademo.service.AccountApi;
import com.awi.rxjavademo.service.CardApi;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.reactivex.schedulers.Schedulers.io;

@Slf4j
@RestController
@AllArgsConstructor
public class Withdrawal2Controller {

    private final CardApi cardApi;
    private final AccountApi accountApi;

    @GetMapping(
            value = "/withdrawal2",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Single<List<Account>> getCards() {

        return cardApi.getCardInfo()
                .toObservable()
                .flatMapIterable(cardResponse -> cardResponse.getAccountList())
                .flatMap(account -> accountApi.getAccountInfo(account.getNumber())
                        .subscribeOn(io())
                        .doOnSuccess(account1 -> log.info("Thread: {} | maxThreads: {}", Thread.currentThread().getName(), Thread.activeCount()))
                        .filter(accountResponse -> accountResponse.getAmount() %2 == 0)
                        .toObservable())
                .toList();
    }
}
