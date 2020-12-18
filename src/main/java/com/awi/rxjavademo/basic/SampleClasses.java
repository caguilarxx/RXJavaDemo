package com.awi.rxjavademo.basic;

import com.awi.rxjavademo.model.Card;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleClasses {

    public static void main(String[] args) {

        Card card = new Card();
        card.setActive(true);
        card.setId(10);
        card.setNumber("100000000");


        Single<Card> demoSingle = Single.just(card);
        demoSingle
                .map(cardx -> cardx.getNumber())
                .doOnSuccess(cardNumber -> {
                    log.info("current thread = {}", Thread.currentThread().getName());
                    log.info(cardNumber);
                });

        demoSingle.subscribe((s, throwable) -> log.info("finish {}", s));
    }
}
