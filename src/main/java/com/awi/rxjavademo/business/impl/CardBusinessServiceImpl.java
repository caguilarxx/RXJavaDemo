package com.awi.rxjavademo.business.impl;


import com.awi.rxjavademo.business.CardBusinessService;
import com.awi.rxjavademo.model.Card;
import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class CardBusinessServiceImpl implements CardBusinessService {

    @Override
    public List<Card> getCards() {
        return generateCardsList();
    }

    @Override
    public Observable<Card> getCardsStream() {
        return generateCardsObservable();
    }

    private List<Card> generateCardsList() {
        return Arrays.asList(
                new Card(1001, "4557885801554491", true),
                new Card(1002, "4557885801554492", true),
                new Card(1003, "4557885801554493", true),
                new Card(1004, "4557885801554494", false)
        );
    }

    private Observable<Card> generateCardsObservable() {
        return Observable.just(
                new Card(1001, "4557885801554491", true),
                new Card(1002, "4557885801554492", true),
                new Card(1003, "4557885801554493", true),
                new Card(1004, "4557885801554494", false)
        )
                .concatMap(card -> Observable.just(card).delay(3, TimeUnit.SECONDS));
    }
}

