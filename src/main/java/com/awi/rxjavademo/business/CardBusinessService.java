package com.awi.rxjavademo.business;


import com.awi.rxjavademo.model.Card;
import io.reactivex.Observable;
import io.reactivex.Single;

import java.util.List;

public interface CardBusinessService {

  List<Card> getCards();

  Observable<Card> getCardsStream();
}
