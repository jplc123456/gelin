package com.glsw.gelin.service;

import com.glsw.gelin.po.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {
    Card getCard(Long id);
    Page<Card> listCard(Pageable pageable);
    Page<Card> listCardAndName(String name,Pageable pageable);
    Card saveCard(Card card);
    Card updateCard(Long id, Card card);
    void deleteCard(Long id);
}
