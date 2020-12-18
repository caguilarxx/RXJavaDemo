package com.awi.rxjavademo.controller;

import com.awi.rxjavademo.business.CardBusinessService;
import com.awi.rxjavademo.model.Card;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.reactivex.schedulers.Schedulers.io;

@Slf4j
@RestController
@AllArgsConstructor
public class CardController {

    private final CardBusinessService service;

    @GetMapping(
            value = "/cards",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getCards() {
        return service.getCards();
    }

    @GetMapping(
            value = "/cards-stream",
            produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    private Observable<Card> getCardsFiltered() {
        return service.getCardsStream()
                .map(card -> card.getNumber())
                .map(cardNumber -> Single.just(cardNumber.length())
                        .map(integer -> String.valueOf(integer))
                        .subscribeOn(io()))
                .map(stringSingle -> stringSingle.blockingGet())
                .map(s -> new Card())
                .subscribeOn(io());
    }
}
